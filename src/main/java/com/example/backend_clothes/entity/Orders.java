package com.example.backend_clothes.entity;

import com.example.backend_clothes.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String userName;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private Double totalPrice; // Tổng giá trị đơn hàng

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Trạng thái đơn hàng (Pending, Completed, Shipped, etc.)

    @Column(nullable = false)
    private String address; // Địa chỉ giao hàng

    @Column(nullable = false)
    private String phoneNumber; // Số điện thoại liên lạc

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod; // Phương thức thanh toán

    private String paymentStatus; // Trạng thái thanh toán (Success, Failed, Pending)

    private LocalDateTime createdAt; // Thời gian tạo đơn hàng
    private LocalDateTime updatedAt; // Thời gian cập nhật đơn hàng
}
