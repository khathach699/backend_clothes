package com.example.backend_clothes.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
     Long cartId;
     Long productId;
     String productName;
     String colorName;
     String sizeName;
     int quantity;
     Double price; // Price based on color and size
     LocalDateTime addedAt;
     String productImage;





}
