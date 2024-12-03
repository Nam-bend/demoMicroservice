package org.aibles.product_service.features.catalog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.product_service.features.catalog.controller.constant.ResponseConstant;
import org.aibles.product_service.features.catalog.controller.converter.ProductConverter;
import org.aibles.product_service.features.catalog.controller.dto.request.ProductRequest;
import org.aibles.product_service.features.catalog.controller.dto.response.BaseResponse;
import org.aibles.product_service.features.catalog.controller.dto.response.ProductResponse;
import org.aibles.product_service.features.catalog.entity.ProductEntity;
import org.aibles.product_service.features.catalog.service.ProductService;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ProductResponse> createProduct ( @RequestBody ProductRequest productRequest )
             {
        log.info("Tạo sản phẩm: {}", productRequest);
        ProductEntity productEntity = ProductConverter.toEntity(productRequest);
        ProductEntity createdProduct = productService.createProduct(productEntity);
        ProductResponse response = ProductConverter.toResponse(createdProduct);
        BaseResponse baseResponse = new BaseResponse<>(ResponseConstant.SUCCESS, new Timestamp(System.currentTimeMillis()), response);
        return baseResponse;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<ProductResponse>> searchProducts(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("Tìm kiếm sản phẩm với keyword: '{}', page: {}, size: {}", keyword, page, size);
        var productsPage = productService.searchProducts(keyword, page, size);
        // Chuyển đổi danh sách sản phẩm từ Entity sang Response DTO
        List<ProductResponse> productResponses = productsPage.getContent().stream()
                .map(ProductConverter::toResponse)
                .collect(Collectors.toList());
        log.info("Tìm thấy {} sản phẩm phù hợp với keyword: '{}'", productResponses.size(), keyword);
        // Trả về kết quả trong BaseResponse
        BaseResponse baseResponse = new BaseResponse<>(ResponseConstant.SUCCESS, new Timestamp(System.currentTimeMillis()), productResponses);
        return baseResponse;
    }


}
