package org.aibles.product_service.features.catalog.controller.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private String name;
    private String description;
    private Long price;
}
