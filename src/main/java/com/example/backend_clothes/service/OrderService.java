package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.request.OrderItemRequest;
import com.example.backend_clothes.dto.request.OrderRequest;
import com.example.backend_clothes.dto.response.OrderResponse;
import com.example.backend_clothes.entity.*;
import com.example.backend_clothes.enums.OrderStatus;
import com.example.backend_clothes.mapper.OrderMapper;
import com.example.backend_clothes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private OrdersRepository orderRepository;
    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Tìm phương thức thanh toán
        PaymentMethod paymentMethod = paymentMethodRepository.findById(orderRequest.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Payment method not found"));

        // Tạo đơn hàng mới
        Orders newOrder = new Orders();
        newOrder.setUser(userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        newOrder.setPaymentMethod(paymentMethod);
        newOrder.setUserName(orderRequest.getUserName());
        newOrder.setAddress(orderRequest.getAddress());
        newOrder.setPhoneNumber(orderRequest.getPhoneNumber());
        newOrder.setStatus(OrderStatus.PENDING);
        newOrder.setPaymentStatus("COMPLETED");
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setUpdatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Products product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            ProductColorSize productColorSize = productColorSizeRepository
                    .findByProductIdAndColorIdAndSizeId(itemRequest.getProductId(), itemRequest.getColorId(), itemRequest.getSizeId());

            if (productColorSize.getQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product with specified color and size");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setProduct(product);
            orderItem.setProductColorSize(productColorSize);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice());

            totalPrice += product.getPrice() * itemRequest.getQuantity();
            orderItems.add(orderItem);

            productColorSize.setQuantity(productColorSize.getQuantity() - itemRequest.getQuantity());
        }

        newOrder.setOrderItems(orderItems);
        newOrder.setTotalPrice(totalPrice);

        Orders savedOrder = orderRepository.save(newOrder);

        return orderMapper.orderToOrderResponse(savedOrder);
    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Orders> userOrders = orderRepository.findByUser(user);

        return orderMapper.ordersToOrderResponses(userOrders);
    }
}
