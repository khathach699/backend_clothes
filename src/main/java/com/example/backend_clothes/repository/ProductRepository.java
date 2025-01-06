package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    @Query("SELECT p FROM Products p JOIN FETCH p.productColorSizes WHERE p.id = :id")
    Optional<Products> findByIdWithColorSizes(Long id);

    @Query("SELECT p FROM Products p JOIN FETCH p.productColorSizes")
    List<Products> findAllWithColorSizes();
}
