package com.suwan.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suwan.redis.common.Cookies;
import com.suwan.redis.domain.user.dto.CustomUserDetails;
import com.suwan.redis.jwt.JwtUtil;
import com.suwan.redis.domain.user.entity.Refresh;
import com.suwan.redis.service.user.RefreshService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

  private final RefreshService refreshService;
  private final AuthenticationManager authenticationManager;
  private final ObjectMapper objectMapper;
  private final JwtUtil jwtUtil;
  private final Cookies cookies;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      Map map = objectMapper.readValue(request.getInputStream(), Map.class);
      String email = map.get("email").toString();
      String password = map.get("password").toString();

      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

      return authenticationManager.authenticate(token);
    } catch (IOException e) {
      throw new AuthenticationServiceException("failed to authenticate", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();

    Long id = user.getId();
    String email = user.getEmail();
    String username = user.getUsername();

    Map<String, Object> claims = new HashMap<>();
    claims.put("email", email);

    String accessToken = jwtUtil.createToken("access", claims, 1000 * 60 * 5L);
    String refreshToken = jwtUtil.createToken("refresh", claims, 1000 * 60 * 60L);

    addRefreshEntity(id, refreshToken, 1000 * 60 * 60 * 24L);

    response.setHeader("Authorization", "Bearer " + accessToken);
    response.addCookie(cookies.createCookie("refreshToken", refreshToken));
    response.setContentType("application/json; charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    response.getWriter().write(objectMapper.writeValueAsString(username));
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    System.out.println("실패여?");
    super.unsuccessfulAuthentication(request, response, failed);
  }

  private void addRefreshEntity(Long userId, String refreshToken, long expiredMs) {
    Date date = new Date(System.currentTimeMillis() + expiredMs);
    Refresh refresh = Refresh.create(userId, refreshToken, date.toString());
    refreshService.saveRefresh(refresh);
  }

}
