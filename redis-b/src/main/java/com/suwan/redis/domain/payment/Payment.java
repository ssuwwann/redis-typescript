package com.suwan.redis.domain.payment;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.domain.user.entity.User;
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
