package org.aibles.product_service.features.catalog.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends ApplicationBaseRuntimeException {
    public ProductAlreadyExistsException(String name) {
        super(HttpStatus.CONFLICT, String.format("Product with name '%s' already exists.", name));
    }
}