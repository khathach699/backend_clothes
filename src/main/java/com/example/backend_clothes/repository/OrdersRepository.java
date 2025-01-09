package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
