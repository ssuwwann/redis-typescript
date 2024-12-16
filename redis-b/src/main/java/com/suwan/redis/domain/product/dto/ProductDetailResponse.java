package com.suwan.redis.domain.product.dto;

import com.suwan.redis.domain.file.dto.ProductFileCommand;
import com.suwan.redis.domain.product.entitiy.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailResponse {
  private Long id;
  private String title;
  private String description;
  private Double originalPrice;
  private Integer discountRate;
  private Double currentPrice;
  private boolean isSpecialPrice;
  private boolean freeDelivery;
  private Integer quantity;
  private List<String> categoryNames;
  private ProductFileCommand productFile;
  private Product.SellerResponse seller;


}
