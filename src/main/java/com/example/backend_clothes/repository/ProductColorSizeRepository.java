package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.ProductColorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorSizeRepository  extends JpaRepository <ProductColorSize, Long> {
}
