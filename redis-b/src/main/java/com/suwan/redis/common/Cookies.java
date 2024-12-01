package com.suwan.redis.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class Cookies {

  public Cookie createCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);

    if (key.startsWith("refresh")) cookie.setMaxAge(24 * 60 * 60); // 토큰을 만들때와 동일하게
    else if (key.startsWith("access")) cookie.setMaxAge(60 * 60);

    cookie.setPath("/");
    cookie.setHttpOnly(true);

    return cookie;
  }

  public void removeCookiesAndSendUnauthorized(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("refreshToken")) {
          Cookie newCookie = new Cookie(cookie.getName(), null);
          newCookie.setMaxAge(0);
          newCookie.setPath("/");
          response.addCookie(newCookie);
        }
      }
    }

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }

}
