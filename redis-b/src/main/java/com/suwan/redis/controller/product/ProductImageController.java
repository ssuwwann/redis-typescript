package com.suwan.redis.controller.product;

import com.suwan.redis.domain.file.FileType;
import com.suwan.redis.domain.file.ProductFile;
import com.suwan.redis.domain.product.entitiy.Product;
import com.suwan.redis.repository.file.ProductFileRepository;
import com.suwan.redis.repository.product.ProductRepository;
import com.suwan.redis.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductImageController {

  private final ProductRepository productRepository;
  private final ProductFileRepository productFileRepository;
  private final FileUtil fileUtil;

  @GetMapping("/images/thumbnail/{productId}")
  public ResponseEntity<Resource> getProductImage(@PathVariable Long productId) {
    Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("찾지 못했습니다."));
    List<ProductFile> productFiles = productFileRepository.findByProduct(product);

    return fileUtil.getThubnail(productFiles);
  }

  @GetMapping("/images/{imageId}")
  public ResponseEntity<Resource> getProductImageDescription(@PathVariable Long imageId) {
    ProductFile productFile = productFileRepository.findById(imageId).orElseThrow();

    return fileUtil.getImageResource(productFile);
  }

}
