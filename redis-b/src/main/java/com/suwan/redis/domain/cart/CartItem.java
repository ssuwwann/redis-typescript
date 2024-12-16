package com.suwan.redis.domain.cart;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.domain.product.entitiy.Product;
import jakarta.persistence.*;

@Entity
public class CartItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CART_ID", nullable = false)
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRODUCT_ID", nullable = false)
  private Product product;

  private int quantity;
}
