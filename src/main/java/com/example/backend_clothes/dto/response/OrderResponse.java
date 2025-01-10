package com.example.backend_clothes.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;  // ID đơn hàng
    private Long userId;  // ID người dùng
    private String userName;
    private Double totalPrice;  // Tổng giá trị đơn hàng
    private String status;  // Trạng thái đơn hàng (Pending, Completed, etc.)
    private String address;  // Địa chỉ giao hàng
    private String phoneNumber;
    private LocalDateTime createdAt;
    // Số điện thoại
    private String paymentMethodName;  // Tên phương thức thanh toán
    private String paymentStatus;  // Trạng thái thanh toán (Success, Failed, Pending)
    private List<OrderItemResponse> orderItems;  // Danh sách sản phẩm trong đơn hàng

    // Getters and Setters
}
