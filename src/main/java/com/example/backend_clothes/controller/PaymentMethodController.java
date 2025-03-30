package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.entity.PaymentMethod;
import com.example.backend_clothes.service.PaymentMethodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentMethodController {

    PaymentMethodService paymentMethodService;

    @GetMapping
    ApiResponse<List<PaymentMethod>> getAllPaymentMethods() {
        return ApiResponse.<List<PaymentMethod>>builder()
                .result(paymentMethodService.findAll())
                .build();
    }
}