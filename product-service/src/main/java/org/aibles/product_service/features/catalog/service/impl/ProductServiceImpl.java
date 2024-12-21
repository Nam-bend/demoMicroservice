package org.aibles.product_service.features.catalog.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.product_service.features.catalog.entity.ProductEntity;
import org.aibles.product_service.features.catalog.exception.ProductAlreadyExistsException;
import org.aibles.product_service.features.catalog.repository.ProductRepository;
import org.aibles.product_service.features.catalog.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        log.info("Creating product {}", productEntity.getName());

        if (productRepository.existsByName(productEntity.getName())) {
            log.error("Product already exists");
            throw new ProductAlreadyExistsException(productEntity.getName());
        }
        productRepository.save(productEntity);
        log.info("Product created successfully with id: {}", productEntity.getId());
        return productEntity;
    }


    @Override
    @Transactional
    public Page<ProductEntity> searchProducts(String keyword, int page, int size) {
        log.info("=== Start API search Products ===");
        log.info("=== Request Body: keyword: {}, page: {}, size: {} ===", keyword, page, size);
        Pageable pageable = PageRequest.of(page, size);

        if (keyword == null || keyword.trim().isEmpty()) {
            log.info("Keyword is null or empty, returning all products");
            Page<ProductEntity> allProducts = productRepository.findAll(pageable);
            log.info("Returning {} products in total", allProducts.getTotalElements());
            return allProducts;
        }
        Page<ProductEntity> products = productRepository.searchByNameOrDescription(keyword, pageable);
        if (products.isEmpty()) {
            log.error("No products found with keyword: {}", keyword);
            return products;
        }
        log.info("Successfully found {} products for keyword: '{}'", products.getTotalElements(), keyword);
        return products;
    }
}

