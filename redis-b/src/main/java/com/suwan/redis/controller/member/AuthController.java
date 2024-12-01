package com.suwan.redis.controller.member;

import com.suwan.redis.common.Cookies;
import com.suwan.redis.entitiy.user.dto.UpdateToken;
import com.suwan.redis.service.user.RefreshService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final RefreshService refreshService;
  private final Cookies cookies;

  @PostMapping("/auth/refresh")
  public ResponseEntity<Void> createNewAccessToken(HttpServletRequest request, HttpServletResponse response) {
    try {
      UpdateToken newToken = refreshService.createNewToken(request);

      Cookie refresh = cookies.createCookie("refreshToken", newToken.getNewRefreshToken());

      response.addCookie(refresh);
      response.setHeader("Authorization", "Bearer " + newToken.getNewAccessToken());

      return ResponseEntity.ok().build();
    } catch (JwtException e) {
      cookies.removeCookiesAndSendUnauthorized(request, response);

      return ResponseEntity.badRequest().build();
    }
  }

}
