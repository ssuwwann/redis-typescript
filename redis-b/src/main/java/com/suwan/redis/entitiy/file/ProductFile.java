package com.suwan.redis.entitiy.file;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductFile extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(nullable = false)
  private String originalFilename;

  @Column(nullable = false)
  private String savedFilename;

  @Column(nullable = false)
  private String savedPath;

  @Column(nullable = false)
  private String extension;

  @Enumerated(EnumType.STRING)
  private FileType fileType;

  @Column(nullable = false)
  private Long fileSize;

  private Integer displayOrder;

  // 상품 이미지 생성
  public static ProductFile of(Product product, String originalFilename, String savedPath, String savedFilename, String extension, Long fileSize, Integer displayOrder) {
    ProductFile file = new ProductFile();
    file.product = product;
    file.originalFilename = originalFilename;
    file.savedFilename = savedFilename;
    file.savedPath = savedPath;
    file.extension = extension;
    file.fileType = FileType.PRODUCT_IMAGE;
    file.fileSize = fileSize;
    file.displayOrder = displayOrder;
    return file;
  }

  // 상품 설명 이미지 생성
  public static ProductFile of(Product product, String originalFilename, String savedPath, String savedFilename, String extension, Long fileSize) {
    ProductFile file = new ProductFile();
    file.product = product;
    file.originalFilename = originalFilename;
    file.savedFilename = savedFilename;
    file.savedPath = savedPath;
    file.extension = extension;
    file.fileType = FileType.DESCRIPTION_IMAGE;
    file.fileSize = fileSize;
    file.displayOrder = null;  // 설명 이미지는 순서가 필요 없음
    return file;
  }

}
