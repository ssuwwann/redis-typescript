package com.suwan.redis.entitiy.product.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPriceInfo {

  @Column(nullable = false)
  private Double originalPrice;

  @Column(nullable = false)
  private Integer discountRate;

  @Column(nullable = false)
  private Double currentPrice;

  @Column(nullable = false)
  private boolean isSpecialPrice;

  @Column(nullable = false)
  private boolean freeDelivery;

  private ProductPriceInfo(Double originalPrice, Integer discountRate, Double currentPrice,
                           boolean isSpecialPrice, boolean freeDelivery) {
    validatePrice(originalPrice);
    validateDiscountRate(discountRate);
    validateCalculatePrice(originalPrice, discountRate, currentPrice);

    this.originalPrice = originalPrice;
    this.discountRate = discountRate;
    this.currentPrice = currentPrice;
    this.isSpecialPrice = isSpecialPrice;
    this.freeDelivery = freeDelivery;
  }

  public static ProductPriceInfo of(Double originalPrice, Integer discountRate,
                                    Double currentPrice, boolean isSpecialPrice, boolean freeDelivery) {
    return new ProductPriceInfo(originalPrice, discountRate, currentPrice, isSpecialPrice, freeDelivery);
  }

  private void validatePrice(Double originalPrice) {
    if (originalPrice == null || originalPrice <= 0)
      throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
  }

  private void validateDiscountRate(Integer discountRate) {
    if (discountRate == null || discountRate < 0 || discountRate > 100)
      throw new IllegalArgumentException("할인율은 0에서 100 사이여야 합니다.");
  }

  private void validateCalculatePrice(Double originalPrice, Integer discountRate, Double currentPrice) {
    double calculatedCurrentPrice = calculateCurrentPrice(originalPrice, discountRate);
    double difference = Math.abs(calculatedCurrentPrice - currentPrice);

    // 부동소수점 연산의 오차를 고려하여 작은 차이는 허용 ? ..
    if (difference > 0.01) {
      throw new IllegalArgumentException(
              String.format("할인가격이 올바르지 않습니다. 계산된 가격: %.2f, 입력된 가격: %.2f",
                      calculatedCurrentPrice, currentPrice));
    }
  }

  private Double calculateCurrentPrice(Double originalPrice, Integer discountRate) {
    return originalPrice * (1 - discountRate / 100.0);
  }

}
