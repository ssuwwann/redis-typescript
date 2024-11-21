package com.suwan.redis.jwt;

import com.suwan.redis.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refresh extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private String refreshToken;

  @Column(nullable = false)
  private String expiration;

  public static Refresh create(Long userId, String refreshToken, String expiration) {
    return Refresh.builder()
            .userId(userId)
            .refreshToken(refreshToken)
            .expiration(expiration)
            .build();
  }

  public void deactivate() {
    this.setDeleted(true);
  }

}
