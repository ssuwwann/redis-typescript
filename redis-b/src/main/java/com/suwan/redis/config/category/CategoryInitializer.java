package com.suwan.redis.config.category;

import com.suwan.redis.domain.category.Category;
import com.suwan.redis.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CategoryInitializer implements ApplicationListener<ContextRefreshedEvent> {

  private final CategoryRepository categoryRepository;
  private static final Map<String, List<String>> CATEGORIES = Map.of(
          "가전", List.of("노트북", "태블릿", "데스크탑", "게임기", "생활가전"),
          "의류", List.of("상의", "하의", "신발", "모자", "잡화"),
          "식품", List.of("밥", "반찬", "김밥식", "고기", "채소"),
          "도서", List.of("추리", "SF소설", "시", "자서전", "전공서적")
  );

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (isInitialized()) return;

    initializeCategories();
  }

  private void initializeCategories() {
    CATEGORIES.forEach((parentName, childrenNames) -> {
      // 1차 카테고리 생성
      Category parentCategory = Category.builder()
              .name(parentName)
              .level(1)
              .build();
      categoryRepository.save(parentCategory);

      // 2차 카테고리 생성
      childrenNames.forEach(childName -> {
        Category childCategory = Category.builder()
                .name(childName)
                .parent(parentCategory)
                .level(2)
                .build();
        categoryRepository.save(childCategory);
      });
    });
  }

  private boolean isInitialized() {
    return categoryRepository.count() > 0;
  }
}
