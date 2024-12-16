package com.suwan.redis.service.user;

import com.suwan.redis.domain.user.entity.User;
import com.suwan.redis.domain.user.dto.UpdateToken;
import com.suwan.redis.domain.user.entity.Refresh;
import com.suwan.redis.jwt.JwtUtil;
import com.suwan.redis.repository.user.RefreshRepository;
import com.suwan.redis.repository.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RefreshService {

  private final RefreshRepository refreshRepository;
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;

  public void saveRefresh(Refresh refresh) {
    refreshRepository.save(refresh);
  }

  @Transactional(readOnly = true)
  public String getRefreshToken(String refreshToken) {
    Refresh refresh = refreshRepository.findByRefreshToken(refreshToken).orElseThrow();
    return refresh.getRefreshToken();
  }

  public UpdateToken createNewToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    String refreshToken = "";
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("refreshToken")) refreshToken = cookie.getValue();
    }

    if (refreshToken.isBlank()) throw new JwtException("No cookie found");

    try {
      jwtUtil.isExpired(refreshToken);
    } catch (ExpiredJwtException e) {
      throw new JwtException("Refresh token expired");
    }

    Refresh oldRefresh = refreshRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new JwtException("Refresh token expired or reuse"));
    deleteRefresh(oldRefresh.getRefreshToken());
    refreshRepository.flush();

    User user = userRepository.findByEmail(jwtUtil.getEmail(refreshToken)).orElseThrow();

    Map<String, Object> claims = new HashMap<>();
    claims.put("email", user.getEmail());

    String newAccessToken = jwtUtil.createToken("access", claims, 1000 * 60 * 5L);
    String newRefreshToken = jwtUtil.createToken("refresh", claims, 1000 * 60 * 60 * 24L);

    Refresh newRefresh = Refresh.create(user.getId(), newRefreshToken, new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24L).toString());
    if (!refreshRepository.findByRefreshToken(newRefreshToken).isPresent()) refreshRepository.save(newRefresh);

    return new UpdateToken(newAccessToken, newRefreshToken);
  }

  public void deleteRefresh(String refreshToken) {
    refreshRepository.deleteRefreshByRefreshToken(refreshToken);
  }
}
