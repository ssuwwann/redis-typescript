package com.suwan.redis.domain.product.entitiy;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.domain.category.Category;
import com.suwan.redis.domain.product.dto.ProductRequest;
import com.suwan.redis.domain.product.vo.ProductBasicInfo;
import com.suwan.redis.domain.product.vo.ProductImages;
import com.suwan.redis.domain.product.vo.ProductInventory;
import com.suwan.redis.domain.product.vo.ProductPriceInfo;
import com.suwan.redis.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SELLER_ID", nullable = false)
  private User seller;

  @Embedded
  private ProductBasicInfo basicInfo;

  @Embedded
  private ProductPriceInfo priceInfo;

  @Embedded
  private ProductInventory inventory;

  @Embedded
  private ProductImages productImages;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<ProductCategory> categories = new ArrayList<>();

  @Builder
  public static class SellerResponse {
    private Long id;
    private String email;
    private String username;
  }


  public static Product create(User seller, ProductRequest request, List<Category> categories) {
    Product product = Product.builder()
            .seller(seller)
            .basicInfo(ProductBasicInfo.of(
                    request.getTitle(),
                    request.getDescription()))
            .priceInfo(ProductPriceInfo.of(
                    request.getOriginalPrice(),
                    request.getDiscountRate(),
                    request.getCurrentPrice(),
                    request.isSpecialPrice(),
                    request.isFreeDelivery()))
            .inventory(ProductInventory.from(request.getQuantity()))
            .build();

    product.addCategories(categories);

    return product;
  }

  public SellerResponse getSeller() {
    return SellerResponse.builder()
            .id(seller.getId())
            .email(seller.getEmail())
            .username(seller.getUsername())
            .build();
  }

  private void addCategories(List<Category> categories) {
    categories.forEach(category -> {
      this.categories.add(ProductCategory.of(this, category));
    });
  }

  public void validateProduct() {
    boolean hasDescription = basicInfo.getDescription() != null && !basicInfo.getDescription().isBlank();
    boolean hasDescriptionProductImage = productImages != null && productImages.hasDescriptionImage();

    if (!hasDescription && !hasDescriptionProductImage)
      throw new IllegalArgumentException("상품 설명 또는 상품 설명 이미지 중 하나는 필수입니다.");
  }

}