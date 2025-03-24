package com.example.backend_clothes.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductColorSizeResponse {
    String colorName;
    String sizeName;
    int quantity;
}
