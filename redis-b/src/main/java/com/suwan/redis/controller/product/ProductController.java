package com.suwan.redis.controller.product;

import com.suwan.redis.entitiy.product.dto.ProductRequest;
import com.suwan.redis.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Void> addProduct(Authentication authentication, final ProductRequest request) throws IOException {
    log.info("Add product {}", request);
    productService.saveProduct(authentication, request);

    return ResponseEntity.ok().build();
  }
}
