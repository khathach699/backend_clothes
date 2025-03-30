package com.example.backend_clothes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private Long productId;  // ID của sản phẩm
    private int quantity;
    private Long colorId; // Add colorId field
    private Long sizeId;
}
