package com.example.backend_clothes.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long userId;  // ID người dùng
    private String userName;
    private Long paymentMethodId;  // ID phương thức thanh toán
    private String address;  // Địa chỉ giao hàng
    private String phoneNumber;  // Số điện thoại liên lạc
    private List<OrderItemRequest> orderItems;  // Danh sách sản phẩm


}
