package com.suwan.redis.entitiy.product;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.category.Category;
import com.suwan.redis.entitiy.file.ProductFile;
import com.suwan.redis.entitiy.payment.PaymentStatus;
import com.suwan.redis.entitiy.product.dto.ProductRequest;
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
  private User user;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductCategory> categories = new ArrayList<>();

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<ProductFile> productFile;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

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

  @Column(nullable = false)
  private String image;

  @Column(nullable = false)
  private Integer quantity;

  public void addCategory(List<Category> categories) {
    categories.forEach(category -> this.categories.add(ProductCategory.of(this, category)));
  }

  public static Product from(User seller, ProductRequest request) {
    return Product.builder()
            .user(seller)
            .title(request.getTitle())
            .description(request.getDescription())
            .originalPrice(request.getOriginalPrice())
            .discountRate(request.getDiscountRate())
            .currentPrice(request.getCurrentPrice())
            .isSpecialPrice(request.isSpecialPrice())
            .freeDelivery(request.isFreeDelivery())
            .quantity(request.getQuantity())
            .build();
  }

}