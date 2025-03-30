package com.example.backend_clothes.dto.response;


import com.example.backend_clothes.entity.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long orderId;  // ID đơn hàng
    Long userId;  // ID người dùng
    String userName;
    Double totalPrice;  // Tổng giá trị đơn hàng
    String status;  // Trạng thái đơn hàng (Pending, Completed, etc.)
    String address;  // Địa chỉ giao hàng
    String phoneNumber;
    LocalDateTime createdAt;
    Long paymentMethodId;
    String paymentMethodName;
    String paymentStatus;  // Trạng thái thanh toán (Success, Failed, Pending)
    List<OrderItemResponse> orderItems;  // Danh sách sản phẩm trong đơn hàng

    public void setPaymentMethodName(String name) {

    }
}
