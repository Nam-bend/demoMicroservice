package org.aibles.product_service.features.catalog.controller.dto.response;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor

public class BaseResponse<T> {
    private String code;
    private Timestamp timestamp;
    private T data;

    public BaseResponse(String code, Timestamp timestamp, T data) {
        this.code = code;
        this.data = data;
        this.timestamp = timestamp;
    }

    public BaseResponse(String code) {
        this.code = code;
    }
}

