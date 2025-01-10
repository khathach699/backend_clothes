package com.example.backend_clothes.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductColorSizeResponse {
    String colorName;
    String sizeName;
    int quantity;

    public String getSize() {
        return sizeName; // Returns the size name
    }

    // Method to get color
    public String getColor() {
        return colorName; // Returns the color name
    }

}
