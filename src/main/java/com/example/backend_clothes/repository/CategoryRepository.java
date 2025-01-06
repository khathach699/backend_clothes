package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository <Category, Long> {
}
