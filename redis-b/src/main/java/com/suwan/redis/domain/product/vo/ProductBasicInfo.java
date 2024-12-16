package com.suwan.redis.domain.product.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductBasicInfo {

  @Column(nullable = false)
  private String title;

  private String description;

  private ProductBasicInfo(String title, String description) {
    validateTitle(title);
    this.title = title;
    this.description = description;
  }

  public static ProductBasicInfo of(String title, String description) {
    return new ProductBasicInfo(title, description);
  }

  private void validateTitle(String title) {
    if (title == null || title.isBlank()) throw new IllegalArgumentException("상품명은 필수입니다.");
    if (title.length() > 100) throw new IllegalArgumentException("상품명은 100자를 초과할 수 없습니다.");
  }

}
