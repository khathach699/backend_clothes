package com.example.backend_clothes.repository;
import com.example.backend_clothes.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Lấy danh sách đánh giá theo sản phẩm
    List<Rating> findByProductId(Long productId);

    // Tính trung bình điểm đánh giá theo sản phẩm
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.product.id = :productId")
    Optional<Double> getAverageRatingByProductId(@Param("productId") Long productId);

    // Đếm tổng số đánh giá theo sản phẩm
    long countByProductId(Long productId);
}
