package com.suwan.redis.entitiy.user;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.cart.Cart;
import com.suwan.redis.entitiy.payment.Payment;
import com.suwan.redis.entitiy.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "CART_ID", nullable = false)
  @OneToOne(fetch = FetchType.LAZY)
  private Cart cart;

  @OneToMany(mappedBy = "user")
  private List<Payment> payments = new ArrayList<>();

  @Comment("로그인 ID")
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String profileImage;

  @Enumerated(EnumType.STRING)
  private Role role;

}
