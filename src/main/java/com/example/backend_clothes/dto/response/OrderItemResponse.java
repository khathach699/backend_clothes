package com.example.backend_clothes.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long productId;  // ID của sản phẩm
    private String productName;  // Tên sản phẩm
    private int quantity;  // Số lượng sản phẩm
    private Double priceAtOrder;  // Giá sản phẩm tại thời điểm đặt hàng

    // Getters and Setters
}
