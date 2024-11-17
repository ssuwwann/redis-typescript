package com.suwan.redis.entitiy.payment;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.product.Product;
import com.suwan.redis.entitiy.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Payment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @OneToMany(mappedBy = "payment")
  private List<PaymentItem> paymentItems = new ArrayList<>();

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

}
