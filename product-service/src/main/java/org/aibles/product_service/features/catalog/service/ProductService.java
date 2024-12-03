package org.aibles.product_service.features.catalog.service;


import org.aibles.product_service.features.catalog.entity.ProductEntity;
import org.springframework.data.domain.Page;


public interface ProductService {
    ProductEntity createProduct(ProductEntity productEntity);
    Page<ProductEntity> searchProducts(String keyword, int page, int size);
}
