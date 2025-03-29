package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Cart;
import com.example.backend_clothes.entity.ProductColorSize;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Component
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    List<Cart> findByUser(User user);
    Cart findByUserAndProductAndProductColorSize(User user, Products product, ProductColorSize productColorSize);
}
