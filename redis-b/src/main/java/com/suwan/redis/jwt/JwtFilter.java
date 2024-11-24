package com.suwan.redis.jwt;

import com.suwan.redis.common.Cookies;
import com.suwan.redis.entitiy.user.dto.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final Cookies cookies;
  private final JwtUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String requestURI = request.getRequestURI();
    if (requestURI.startsWith("/logout")) return true;
    return false;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");
    String accessToken = "";
    if (authorization != null && authorization.startsWith("Bearer ")) {
      accessToken = authorization.substring(7);
    }

    if (accessToken.isBlank()) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      jwtUtil.isExpired(accessToken);
    } catch (ExpiredJwtException eje) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    String type = jwtUtil.getType(accessToken);
    if (!type.startsWith("access")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(jwtUtil.getEmail(accessToken));
      String authority = user.getAuthorities().iterator().next().getAuthority();

      UsernamePasswordAuthenticationToken token
              = new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority("ROLE_" + authority)));

      SecurityContextHolder.getContext().setAuthentication(token);
      filterChain.doFilter(request, response);
    } catch (BadCredentialsException e) {
      cookies.removeCookiesAndSendUnauthorized(request, response);
    }

  }

}
