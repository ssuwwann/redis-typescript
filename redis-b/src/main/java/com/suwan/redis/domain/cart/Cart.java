package com.suwan.redis.domain.cart;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "cart")
  private User user;

  @OneToMany(mappedBy = "cart")
  private List<CartItem> cartItems = new ArrayList<>();

  public static Cart createEmpty() {
    return Cart.builder().build();
  }
}
