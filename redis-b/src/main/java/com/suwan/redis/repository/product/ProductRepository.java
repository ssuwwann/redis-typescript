package com.suwan.redis.repository.product;

import com.suwan.redis.domain.product.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
