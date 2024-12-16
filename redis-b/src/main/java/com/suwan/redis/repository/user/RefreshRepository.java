package com.suwan.redis.repository.user;

import com.suwan.redis.domain.user.entity.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {
  void deleteRefreshByRefreshToken(String refreshToken);

  Optional<Refresh> findByRefreshToken(String refreshToken);
}
