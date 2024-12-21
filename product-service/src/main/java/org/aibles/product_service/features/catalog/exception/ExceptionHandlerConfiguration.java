package org.aibles.product_service.features.catalog.exception;



import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerConfiguration {


    // Xử lý ApplicationBaseRuntimeException
    @ExceptionHandler(ApplicationBaseRuntimeException.class)
    public ResponseEntity<Object> handleApplicationBaseRuntimeException(ApplicationBaseRuntimeException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(ex.getStatus(), ex.getMessage()));
    }

    // Xử lý lỗi validate và trả về cả status
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse);
    }

    // Lớp phản hồi cho lỗi validate
    record ValidationErrorResponse(int status, String message, Map<String, String> errors) {}

    // Lớp ErrorResponse
    record ErrorResponse(int status, String message) {}

}
