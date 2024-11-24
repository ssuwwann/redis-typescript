package com.suwan.redis.config;

import com.suwan.redis.jwt.JwtUtil;
import com.suwan.redis.service.user.RefreshService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilter {

  private final RefreshService refreshService;
  private final JwtUtil jwtUtil;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
  }

  private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String requestURI = request.getRequestURI();
    if (!requestURI.startsWith("/logout")) {
      filterChain.doFilter(request, response);
      return;
    }

    String method = request.getMethod();
    if (!method.equals("POST")) {
      filterChain.doFilter(request, response);
      return;
    }

    String refreshToken = "";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().startsWith("refresh")) refreshToken = cookie.getValue();
    }

    // refresh null check
    if (refreshToken.isBlank()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    try {
      jwtUtil.isExpired(refreshToken);
    } catch (ExpiredJwtException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    // check refresh
    String type = jwtUtil.getType(refreshToken);
    if (!type.startsWith("refresh")) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    // db check
    String token = refreshService.getRefreshToken(refreshToken);
    if (token == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    refreshService.deleteRefresh(refreshToken);

    Cookie cookie = new Cookie("refresh", null);
    cookie.setMaxAge(0);
    cookie.setPath("/");

    response.addCookie(cookie);
    response.setStatus(HttpServletResponse.SC_OK);
  }

}
