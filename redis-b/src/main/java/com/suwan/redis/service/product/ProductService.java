package com.suwan.redis.service.product;

import com.suwan.redis.domain.category.Category;
import com.suwan.redis.domain.file.ProductFile;
import com.suwan.redis.domain.file.dto.FileInfomation;
import com.suwan.redis.domain.file.dto.ProductFileCommand;
import com.suwan.redis.domain.product.dto.ProductListResponse;
import com.suwan.redis.domain.product.dto.ProductRequest;
import com.suwan.redis.domain.product.entitiy.Product;
import com.suwan.redis.domain.product.mapper.ProductMapper;
import com.suwan.redis.domain.user.entity.User;
import com.suwan.redis.domain.user.dto.CustomUserDetails;
import com.suwan.redis.repository.category.CategoryRepository;
import com.suwan.redis.repository.file.ProductFileRepository;
import com.suwan.redis.repository.product.ProductRepository;
import com.suwan.redis.repository.user.UserRepository;
import com.suwan.redis.util.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final ProductFileRepository productFileRepository;
  private final FileUpload fileUpload;
  private final ProductMapper productMapper;

  public void saveProduct(Authentication authentication, ProductRequest request) throws IOException {
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
    User seller = userRepository.findByEmail(user.getEmail()).orElseThrow();

    List<Category> categories = request.getCategory()
            .stream()
            .map(category -> categoryRepository.findByName(category).orElseThrow())
            .collect(Collectors.toList());

    Product product = Product.create(seller, request, categories);
    productRepository.save(product);

    ProductFileCommand productFileCommand = handleProductImages(request);

    if (productFileCommand.descriptionImage() != null) {
      ProductFile descriptionImage = ProductFile.createDescriptionImage(product, productFileCommand.descriptionImage());
      productFileRepository.save(descriptionImage);
    }

    if (productFileCommand.productImages() != null) {
      for (int i = 0; i < productFileCommand.productImages().size(); i++) {
        ProductFile productImage = ProductFile.createProductImage(product, productFileCommand.productImages().get(i), i);
        productFileRepository.save(productImage);
      }
    }

    product.validateProduct();
  }

  private ProductFileCommand handleProductImages(ProductRequest request) throws IOException {
    FileInfomation descriptionImageInfo = null;

    if (request.getDescriptionImage() != null) {
      String savePath = fileUpload.uploadFile(request.getDescriptionImage());
      descriptionImageInfo = FileInfomation.of(request.getDescriptionImage(), savePath);
    }

    List<FileInfomation> productImagesInfo = new ArrayList<>();
    if (request.getProductImages() != null) {
      List<String> savedPaths = fileUpload.uploadFiles(request.getProductImages());

      for (int i = 0; i < savedPaths.size(); i++) {
        FileInfomation fileInfo = FileInfomation.of(request.getProductImages().get(i), savedPaths.get(i));
        productImagesInfo.add(fileInfo);
      }
    }

    return new ProductFileCommand(descriptionImageInfo, productImagesInfo);
  }

  @Transactional(readOnly = true)
  public List<ProductListResponse> findAllProduct() {
    List<Product> products = productRepository.findAll();
    return productMapper.toListResponses(products);
  }
}
