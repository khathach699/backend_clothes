package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Orders;
import com.example.backend_clothes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);
    @Query("SELECT COUNT(o) > 0 FROM Orders o " +
            "JOIN OrderItem oi ON o.id = oi.order.id " +
            "WHERE o.user.id = :userId AND oi.product.id = :productId")
    boolean hasUserPurchasedProduct(@Param("userId") Long userId, @Param("productId") Long productId);
}
