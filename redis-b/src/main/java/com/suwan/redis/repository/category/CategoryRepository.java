package com.suwan.redis.repository.category;

import com.suwan.redis.entitiy.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
