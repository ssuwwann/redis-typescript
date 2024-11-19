package com.suwan.redis.repository.cart;

import com.suwan.redis.entitiy.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
