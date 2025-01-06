package com.example.backend_clothes.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;
    String name;
    double price;
    String description;
    String image;
    String categoryName;
    List<ProductColorSizeResponse> colorSizes; // Corrected this line to match the response structure

    public ProductResponse(Long id, String name, Double price, String description, String image, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.categoryName = categoryName;
    }

    public ProductResponse(String name, Double price, String description) {
    }
}
