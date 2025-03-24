package com.example.backend_clothes.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {
    Long productId;  // ID của sản phẩm
    String productName;  // Tên sản phẩm
    int quantity;  // Số lượng sản phẩm
    Double priceAtOrder;
    Long colorId; // Add colorId field
    Long sizeId;// Giá sản phẩm tại thời điểm đặt hàng
}

