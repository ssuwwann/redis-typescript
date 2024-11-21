package com.suwan.redis.service.user;

import com.suwan.redis.entitiy.user.dto.UpdateToken;
import com.suwan.redis.jwt.Refresh;
import com.suwan.redis.repository.user.RefreshRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshService {

  private final RefreshRepository refreshRepository;

  public void saveRefresh(Refresh refresh) {
    refreshRepository.save(refresh);
  }

  public UpdateToken createNewToken(HttpServletRequest request) {

    return null;
  }

  public void deleteRefresh(String refreshToken) {
    refreshRepository.deleteRefreshByRefreshToken(refreshToken);
  }
}
