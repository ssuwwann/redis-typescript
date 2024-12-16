package com.suwan.redis.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListResponse {
  private Long id;
  private String title;
  private Double originalPrice;
  private Integer discountRate;
  private Double currentPrice;
  private boolean isSpecialPrice;
  private String delivery;
  private String thumbnailPath;
}
