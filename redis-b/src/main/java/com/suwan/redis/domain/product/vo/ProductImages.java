package com.suwan.redis.domain.product.vo;

import com.suwan.redis.domain.file.FileType;
import com.suwan.redis.domain.file.ProductFile;
import com.suwan.redis.domain.file.dto.FileInfomation;
import com.suwan.redis.domain.file.dto.ProductFileCommand;
import com.suwan.redis.domain.product.entitiy.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImages {

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductFile> images = new ArrayList<>();

  public static ProductImages of(Product product, ProductFileCommand command) {
    ProductImages productImages = new ProductImages();

    if (command.descriptionImage() != null)
      productImages.images.add(ProductFile.createDescriptionImage(product, command.descriptionImage()));


    if (command.productImages() != null) {
      for (int i = 0; i < command.productImages().size(); i++) {
        FileInfomation fileInfomation = command.productImages().get(i);
        productImages.images.add(ProductFile.createProductImage(product, fileInfomation, i));
      }
    }

    return productImages;
  }

  public boolean hasDescriptionImage() {
    return images.stream().
            anyMatch(image -> image.getFileType() == FileType.DESCRIPTION_IMAGE);
  }
}
