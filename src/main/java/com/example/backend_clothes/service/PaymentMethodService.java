package com.example.backend_clothes.service;

import com.example.backend_clothes.entity.PaymentMethod;
import com.example.backend_clothes.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }
}
