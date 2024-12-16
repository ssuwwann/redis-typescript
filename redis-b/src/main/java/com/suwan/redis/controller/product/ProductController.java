package com.suwan.redis.controller.product;

import com.suwan.redis.domain.product.dto.ProductListResponse;
import com.suwan.redis.domain.product.dto.ProductRequest;
import com.suwan.redis.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Void> addProduct(Authentication authentication, final ProductRequest request) throws IOException {
    productService.saveProduct(authentication, request);

    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<ProductListResponse>> getProducts() {
    List<ProductListResponse> response = productService.findAllProduct();
    return ResponseEntity.ok(response);
  }
}
