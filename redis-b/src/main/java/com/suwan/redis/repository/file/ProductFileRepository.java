package com.suwan.redis.repository.file;

import com.suwan.redis.domain.file.ProductFile;
import com.suwan.redis.domain.product.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
  List<ProductFile> findByProduct(Product product);
}
