package org.aibles.product_service.features.catalog.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Price must not be null")
    @Min(value = 100, message = "Price must be greater than or equal to 100")
    private Long price;
}

