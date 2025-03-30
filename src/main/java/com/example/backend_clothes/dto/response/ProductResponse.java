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
    List<ProductColorSizeResponse> colorSizes;


}
