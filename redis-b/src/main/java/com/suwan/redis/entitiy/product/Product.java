package com.suwan.redis.entitiy.product;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.category.Category;
import com.suwan.redis.entitiy.payment.PaymentStatus;
import com.suwan.redis.entitiy.user.User;
import jakarta.persistence.*;

@Entity
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SELLER_ID", nullable = false)
  private User user;

  @JoinColumn(name = "CATEGORY_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Long originalPrice;

  @Column(nullable = false)
  private int discountRate;

  @Column(nullable = false)
  private Long currentPrice;

  @Column(nullable = false)
  private boolean isSpecialPrice;

  @Column(nullable = false)
  private boolean freeDelivery;

  @Column(nullable = false)
  private String image;

  @Column(nullable = false)
  private int quantity;
}