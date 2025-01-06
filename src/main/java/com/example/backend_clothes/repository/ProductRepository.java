package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository <Products, Long> {
}
