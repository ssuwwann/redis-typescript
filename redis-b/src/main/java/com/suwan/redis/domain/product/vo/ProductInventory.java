package com.suwan.redis.domain.product.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductInventory {

  @Column(nullable = false)
  private Integer quantity;

  private ProductInventory(Integer quantity) {
    validateQuantity(quantity);
    this.quantity = quantity;
  }

  public static ProductInventory from(Integer quantity) {
    return new ProductInventory(quantity);
  }

  private void validateQuantity(Integer quantity) {
    if (quantity == null || quantity < 0) throw new IllegalArgumentException("수량은 0 이상이여야 합니다.");
  }

}
