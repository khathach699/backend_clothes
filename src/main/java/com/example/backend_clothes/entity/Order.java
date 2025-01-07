package com.example.backend_clothes.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @Column(nullable = false)
    private Double totalPrice; // Tổng giá trị đơn hàng

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Trạng thái đơn hàng (Pending, Completed, Shipped, etc.)

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
