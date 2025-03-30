package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query("SELECT c FROM Category c " +
//            "JOIN FETCH c.products p " +
//            "JOIN FETCH p.productColorSizes pcs " +
//            "WHERE c.id = :id")
//    Optional<Category> findCategoryWithProductsById(Long id);


}

