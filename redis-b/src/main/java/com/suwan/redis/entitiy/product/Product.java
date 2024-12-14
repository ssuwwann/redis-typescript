package com.suwan.redis.entitiy.product;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.category.Category;
import com.suwan.redis.entitiy.product.dto.ProductRequest;
import com.suwan.redis.entitiy.product.vo.ProductBasicInfo;
import com.suwan.redis.entitiy.product.vo.ProductImages;
import com.suwan.redis.entitiy.product.vo.ProductInventory;
import com.suwan.redis.entitiy.product.vo.ProductPriceInfo;
import com.suwan.redis.entitiy.user.User;
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