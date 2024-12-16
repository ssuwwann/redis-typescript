package com.suwan.redis.domain.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductRequest {
  private List<String> category;
  private String title;
  private String description;
  private int discountRate;
  private Double originalPrice;
  private Double currentPrice;
  private boolean isSpecialPrice;
  private boolean freeDelivery;
  private int quantity;
  private MultipartFile descriptionImage;
  private List<MultipartFile> productImages;
}
