package org.aibles.product_service.features.catalog.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ApplicationBaseRuntimeException extends RuntimeException {
    private final int status;
    private final String message;

    public ApplicationBaseRuntimeException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
        this.message = message;
    }

}