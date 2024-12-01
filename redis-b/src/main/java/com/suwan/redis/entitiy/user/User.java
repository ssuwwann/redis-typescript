package com.suwan.redis.entitiy.user;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.cart.Cart;
import com.suwan.redis.entitiy.payment.Payment;
import com.suwan.redis.entitiy.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String address;

  private String profileImage;

  @Enumerated(EnumType.STRING)
  private Role role;

  public static User from(UserRequest dto, Cart cart) {
    return User.builder()
            .cart(cart)
            .email(dto.email())
            .password(dto.password())
            .username(dto.username())
            .address(dto.address())
            .role(Role.BU)
            .build();
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void toSeller() {
    role = Role.SE;
  }
}
