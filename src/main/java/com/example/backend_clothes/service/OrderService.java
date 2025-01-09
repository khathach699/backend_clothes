package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.request.OrderItemRequest;
import com.example.backend_clothes.dto.request.OrderRequest;
import com.example.backend_clothes.dto.response.OrderItemResponse;
import com.example.backend_clothes.dto.response.OrderResponse;
import com.example.backend_clothes.entity.*;
import com.example.backend_clothes.enums.OrderStatus;
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

    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Tìm phương thức thanh toán
        PaymentMethod paymentMethod = paymentMethodRepository.findById(orderRequest.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Payment method not found"));

        // Tạo đơn hàng mới
        Orders newOrder = new Orders();
        newOrder.setUser(userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        newOrder.setPaymentMethod(paymentMethod);
        newOrder.setUserName(orderRequest.getUserName());
        newOrder.setAddress(orderRequest.getAddress());
        newOrder.setPhoneNumber(orderRequest.getPhoneNumber());
        newOrder.setStatus(OrderStatus.PENDING);  // Trạng thái ban đầu là PENDING
        newOrder.setPaymentStatus("Pending");  // Trạng thái thanh toán ban đầu là Pending
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setUpdatedAt(LocalDateTime.now());

        // Tạo danh sách các sản phẩm trong đơn hàng
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            Products product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setOrder(newOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice());  // Giá sản phẩm tại thời điểm đặt hàng

            totalPrice += product.getPrice() * itemRequest.getQuantity();  // Cộng dồn giá trị sản phẩm vào tổng đơn hàng
            orderItems.add(orderItem);
        }

        newOrder.setOrderItems(orderItems);  // Thêm các sản phẩm vào đơn hàng
        newOrder.setTotalPrice(totalPrice);  // Cập nhật tổng giá trị đơn hàng

        // Lưu đơn hàng vào cơ sở dữ liệu
        Orders savedOrder = orderRepository.save(newOrder);

        // Tạo phản hồi
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(savedOrder.getId());
        orderResponse.setUserId(savedOrder.getUser().getId());
        orderResponse.setUserName(savedOrder.getUserName());
        orderResponse.setTotalPrice(savedOrder.getTotalPrice());
        orderResponse.setStatus(savedOrder.getStatus().name());
        orderResponse.setPaymentMethodName(savedOrder.getPaymentMethod().getName());
        orderResponse.setPaymentStatus(savedOrder.getPaymentStatus());
        orderResponse.setAddress(savedOrder.getAddress());
        orderResponse.setPhoneNumber(savedOrder.getPhoneNumber());

        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItem orderItem : savedOrder.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductId(orderItem.getProduct().getId());
            orderItemResponse.setProductName(orderItem.getProduct().getName());
            orderItemResponse.setQuantity(orderItem.getQuantity());
            orderItemResponse.setPriceAtOrder(orderItem.getPriceAtOrder());
            orderItemResponses.add(orderItemResponse);
        }

        orderResponse.setOrderItems(orderItemResponses);

        return orderResponse;
    }

}
