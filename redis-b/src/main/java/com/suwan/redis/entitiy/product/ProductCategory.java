package com.suwan.redis.entitiy.product;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.category.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  public static ProductCategory of(final Product product, final Category category) {
    ProductCategory productCategory = new ProductCategory();
    productCategory.product = product;
    productCategory.category = category;
    return productCategory;
  }

}
