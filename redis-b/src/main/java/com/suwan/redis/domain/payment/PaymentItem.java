package com.suwan.redis.domain.payment;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.domain.product.entitiy.Product;
import jakarta.persistence.*;

@Entity
public class PaymentItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Payment payment;

  @ManyToOne(fetch = FetchType.LAZY)
  private Product product;

  private int quantity;

  private Long price;
}
