package com.example.backend_clothes.controller;


import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.CategoryResponse;
import com.example.backend_clothes.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategoriesWithProducts() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategoriesWithProducts())
                .build();
    }

    @GetMapping("search/{id}")
    ApiResponse<CategoryResponse> getCategoryWithProductsById(@PathVariable Long id) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.findCategoryWithProductsById(id))
                .build();
    }
}
