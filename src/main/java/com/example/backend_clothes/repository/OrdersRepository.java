package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Orders;
import com.example.backend_clothes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);
}
