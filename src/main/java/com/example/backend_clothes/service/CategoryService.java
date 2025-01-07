package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.response.CategoryResponse;
import com.example.backend_clothes.dto.response.ProductColorSizeResponse;
import com.example.backend_clothes.dto.response.ProductResponse;
import com.example.backend_clothes.entity.Category;
import com.example.backend_clothes.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Constructor Injection
    public List<CategoryResponse> getAllCategoriesWithProducts() {
        List<Category> categories = categoryRepository.findAll();
        log.info("Fetched Categories: {}", categories); // Debugging log

        return categories.stream().map(this::mapToCategoryResponse).collect(Collectors.toList());
    }

    public CategoryResponse findCategoryWithProductsById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));
        log.info("Fetched Category by ID {}: {}", id, category); // Debugging log
        return mapToCategoryResponse(category);
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        List<ProductResponse> productResponses = category.getProducts().stream()
                .map(product -> {
                    log.info("Mapping Product: {}", product); // Debugging log
                    log.info("Product Color Sizes: {}", product.getColorSizes()); // Debugging log for product color sizes

                    return new ProductResponse(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getDescription(),
                            product.getImage(),
                            category.getName(),
                            product.getColorSizes().stream()
                                    .map(colorSize -> {
                                        log.info("Mapping ColorSize: {}", colorSize); // Debugging log
                                        return new ProductColorSizeResponse(
                                                colorSize.getColorName(),
                                                colorSize.getSizeName(),
                                                colorSize.getQuantity()
                                        );
                                    })
                                    .collect(Collectors.toList())
                    );
                })
                .collect(Collectors.toList());

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getImage(),
                productResponses
        );
    }


}
