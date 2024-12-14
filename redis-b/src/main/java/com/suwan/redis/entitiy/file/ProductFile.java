package com.suwan.redis.entitiy.file;

import com.suwan.redis.common.BaseEntity;
import com.suwan.redis.entitiy.file.dto.FileInfomation;
import com.suwan.redis.entitiy.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
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

  public static ProductFile createProductImage(Product product, FileInfomation FileInfomation, Integer displayOrder) {
    return ProductFile.builder()
            .product(product)
            .originalFilename(FileInfomation.getOriginalFilename())
            .savedFilename(FileInfomation.getSavedFilename())
            .savedPath(FileInfomation.getSavedPath())
            .extension(FileInfomation.getExtension())
            .fileType(FileType.PRODUCT_IMAGE)
            .fileSize(FileInfomation.getFileSize())
            .displayOrder(displayOrder)
            .build();
  }

  public static ProductFile createDescriptionImage(Product product, FileInfomation FileInfomation) {
    return ProductFile.builder()
            .product(product)
            .originalFilename(FileInfomation.getOriginalFilename())
            .savedFilename(FileInfomation.getSavedFilename())
            .savedPath(FileInfomation.getSavedPath())
            .extension(FileInfomation.getExtension())
            .fileType(FileType.DESCRIPTION_IMAGE)
            .fileSize(FileInfomation.getFileSize())
            .displayOrder(null)
            .build();
  }

}
