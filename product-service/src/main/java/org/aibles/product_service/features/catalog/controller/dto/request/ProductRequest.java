package org.aibles.product_service.features.catalog.controller.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Long price;
}

