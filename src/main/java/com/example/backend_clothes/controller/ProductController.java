package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.ProductResponse;
import com.example.backend_clothes.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping("/getAlls")
        // Path to retrieve all products
    ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/sortedByPrice")
    ApiResponse<List<ProductResponse>> getProductsSortedByPrice() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getProductsByPriceDesc())
                .build();
    }

    @GetMapping("/sortedByPriceAsc")
    ApiResponse<List<ProductResponse>> getProductsSortedByPriceAsc() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getProductsByPriceAsc())
                .build();
    }

    @GetMapping("/most-purchased")
    ApiResponse<List<ProductResponse>> getMostPurchasedProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getMostPurchasedProducts())
                .build();
    }

}