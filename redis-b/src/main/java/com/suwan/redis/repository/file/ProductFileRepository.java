package com.suwan.redis.repository.file;

import com.suwan.redis.domain.file.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
}
