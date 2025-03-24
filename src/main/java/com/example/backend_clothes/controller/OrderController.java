package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.OrderRequest;
import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.OrderResponse;
import com.example.backend_clothes.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PostMapping("/add")
    ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(orderRequest))
                .build();
    }

    @GetMapping("/user/{userId}")
    ApiResponse<List<OrderResponse>> getOrdersByUserId(@PathVariable Long userId) {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getOrdersByUserId(userId))
                .build();

    }
}
