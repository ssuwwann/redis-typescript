package com.suwan.redis.domain.product.mapper;

import com.suwan.redis.domain.file.FileType;
import com.suwan.redis.domain.file.ProductFile;
import com.suwan.redis.domain.product.dto.ProductDetailResponse;
import com.suwan.redis.domain.product.dto.ProductListResponse;
import com.suwan.redis.domain.product.entitiy.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

  public List<ProductListResponse> toListResponses(List<Product> products) {
    return products.stream()
            .map(this::createListResponse)
            .collect(Collectors.toList());
  }

  private ProductListResponse createListResponse(Product product) {
    ProductListResponse response = new ProductListResponse();
    response.setId(product.getId());
    response.setTitle(product.getBasicInfo().getTitle());
    response.setOriginalPrice(product.getPriceInfo().getOriginalPrice());
    response.setDiscountRate(product.getPriceInfo().getDiscountRate());
    response.setCurrentPrice(product.getPriceInfo().getCurrentPrice());
    response.setSpecialPrice(product.getPriceInfo().isSpecialPrice());
    response.setDelivery(product.getPriceInfo().isFreeDelivery() ? "무료배송" : "");

    return response;
  }

  public ProductDetailResponse createDetailResponse(Product product) {
    ProductDetailResponse response = new ProductDetailResponse();
    response.setId(product.getId());
    response.setTitle(product.getBasicInfo().getTitle());
    response.setDescription(product.getBasicInfo().getDescription());
    response.setOriginalPrice(product.getPriceInfo().getOriginalPrice());
    response.setDiscountRate(product.getPriceInfo().getDiscountRate());
    response.setCurrentPrice(product.getPriceInfo().getCurrentPrice());
    response.setSpecialPrice(product.getPriceInfo().isSpecialPrice());
    response.setFreeDelivery(product.getPriceInfo().isFreeDelivery());
    response.setQuantity(product.getInventory().getQuantity());
    response.setCategoryNames(product.getCategories().stream()
            .map(pc -> pc.getCategory().getName())
            .collect(Collectors.toList()));
    response.setSeller(product.getSeller());

    // 이미지 매칭
    List<ProductFile> productFiles = product.getProductImages().getImages();

    Long descriptionImage = productFiles.stream()
            .filter(file -> file.getFileType() == FileType.DESCRIPTION_IMAGE)
            .findFirst()
            .map(this::convertToFilePk)
            .orElse(null);

    List<Long> productImages = productFiles.stream()
            .filter(file -> file.getFileType() == FileType.PRODUCT_IMAGE)
            .sorted(Comparator.comparing(ProductFile::getDisplayOrder))
            .map(this::convertToFilePk)
            .collect(Collectors.toList());

    response.setProductImagesId(new ProductDetailResponse.ProductImagesId(descriptionImage, productImages));

    return response;
  }

  private Long convertToFilePk(ProductFile productFile) {
    return productFile.getId();
  }

}
