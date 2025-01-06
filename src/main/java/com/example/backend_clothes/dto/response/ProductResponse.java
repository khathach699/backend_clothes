package com.example.backend_clothes.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
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
}
