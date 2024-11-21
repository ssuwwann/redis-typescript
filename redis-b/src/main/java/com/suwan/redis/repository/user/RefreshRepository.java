package com.suwan.redis.repository.user;

import com.suwan.redis.jwt.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {
  void deleteRefreshByRefreshToken(String refreshToken);
}
