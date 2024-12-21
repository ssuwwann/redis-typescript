package com.suwan.redis.domain.user.entity;

import com.suwan.redis.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE refresh r SET r.is_deleted = true where r.id = ?")
@SQLRestriction("is_deleted = false")
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

  public void update(Long userId, String refreshToken, String expiration) {
    this.refreshToken = refreshToken;
    this.expiration = expiration;
    this.userId = userId;
  }

}
