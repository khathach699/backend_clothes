package com.example.backend_clothes.controller;



import com.example.backend_clothes.dto.response.CategoryResponse;
import com.example.backend_clothes.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Endpoint lấy tất cả các danh mục cùng sản phẩm
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesWithProducts() {
        List<CategoryResponse> categoryResponses = categoryService.getAllCategoriesWithProducts();
        return ResponseEntity.ok(categoryResponses);  // Trả về danh sách CategoryResponse
    }

    @GetMapping("search/{id}")
    public ResponseEntity<CategoryResponse> getCategoryWithProductsById(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.findCategoryWithProductsById(id);
        return ResponseEntity.ok(categoryResponse);
    }
}
