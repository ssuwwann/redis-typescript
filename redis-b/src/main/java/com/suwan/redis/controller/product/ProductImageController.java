package com.suwan.redis.controller.product;

import com.suwan.redis.domain.file.ProductFile;
import com.suwan.redis.domain.product.entitiy.Product;
import com.suwan.redis.repository.file.ProductFileRepository;
import com.suwan.redis.repository.product.ProductRepository;
import com.suwan.redis.util.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductImageController {

  private final ProductRepository productRepository;
  private final ProductFileRepository productFileRepository;
  private final FileUpload fileUpload;

  @GetMapping("/images/{productId}")
  public ResponseEntity<Resource> getProductImage(@PathVariable Long productId) {
    Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("찾지 못했습니다."));
    List<ProductFile> productFiles = productFileRepository.findByProduct(product);

    return fileUpload.getThubnail(productFiles);
  }

}
