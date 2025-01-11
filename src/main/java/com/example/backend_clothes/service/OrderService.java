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
    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Find payment method
        PaymentMethod paymentMethod = paymentMethodRepository.findById(orderRequest.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Payment method not found"));

        // Create new order
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
            // Fetch product and validate
            Products product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Fetch ProductColorSize and validate
            ProductColorSize productColorSize = productColorSizeRepository
                    .findByProductIdAndColorIdAndSizeId(itemRequest.getProductId(), itemRequest.getColorId(), itemRequest.getSizeId());


            if (productColorSize.getQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product with specified color and size");
            }

            // Create OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setProduct(product);
            orderItem.setProductColorSize(productColorSize);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice());

            // Update total price and add to list
            totalPrice += product.getPrice() * itemRequest.getQuantity();
            orderItems.add(orderItem);

            // Decrease stock quantity
            productColorSize.setQuantity(productColorSize.getQuantity() - itemRequest.getQuantity());
        }

        newOrder.setOrderItems(orderItems);
        newOrder.setTotalPrice(totalPrice);

        // Save order and return response
        Orders savedOrder = orderRepository.save(newOrder);

        // Map to response
        return mapToOrderResponse(savedOrder);
    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        // Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get user's orders
        List<Orders> userOrders = orderRepository.findByUser(user);

        // Convert orders to responses
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Orders order : userOrders) {
            orderResponses.add(mapToOrderResponse(order));
        }

        return orderResponses;
    }

    private OrderResponse mapToOrderResponse(Orders order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setUserId(order.getUser().getId());
        orderResponse.setUserName(order.getUserName());
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setStatus(order.getStatus().name());
        orderResponse.setPaymentMethodName(order.getPaymentMethod().getName());
        orderResponse.setPaymentStatus(order.getPaymentStatus());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setPhoneNumber(order.getPhoneNumber());

        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductId(orderItem.getProduct().getId());
            orderItemResponse.setProductName(orderItem.getProduct().getName());
            orderItemResponse.setQuantity(orderItem.getQuantity());
            orderItemResponse.setPriceAtOrder(orderItem.getPriceAtOrder());
            orderItemResponse.setColorId(orderItem.getProductColorSize().getColor().getId());
            orderItemResponse.setSizeId(orderItem.getProductColorSize().getSize().getId());
            orderItemResponses.add(orderItemResponse);
        }

        orderResponse.setOrderItems(orderItemResponses);
        return orderResponse;
    }
}
