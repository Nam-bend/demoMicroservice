package org.aibles.product_service.features.catalog.controller.converter;

import org.aibles.product_service.features.catalog.controller.dto.request.ProductRequest;
import org.aibles.product_service.features.catalog.controller.dto.response.ProductResponse;
import org.aibles.product_service.features.catalog.entity.ProductEntity;

public class ProductConverter {

    public static ProductEntity toEntity(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        return productEntity;
    }

    public static ProductResponse toResponse(ProductEntity productEntity) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(productEntity.getName());
        productResponse.setDescription(productEntity.getDescription());
        productResponse.setPrice(productEntity.getPrice());
        return productResponse;
    }
}

