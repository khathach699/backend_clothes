package com.example.backend_clothes.service;

import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.Rating;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.dto.request.RatingRequest;
import com.example.backend_clothes.dto.response.RatingResponse;
import com.example.backend_clothes.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    // Thêm mới một đánh giá
    public RatingResponse addRating(RatingRequest request) {
        if (request.getScore() < 1 || request.getScore() > 5) {
            throw new IllegalArgumentException("Rating score must be between 1 and 5.");
        }
        Rating rating = new Rating();
        rating.setUser(new User(request.getUserId()));
        rating.setProduct(new Products(request.getProductId()));
        rating.setScore(request.getScore());
        Rating savedRating = ratingRepository.save(rating);

        return mapToResponse(savedRating);
    }

    // Lấy danh sách đánh giá theo sản phẩm
    public List<RatingResponse> getRatingsByProduct(Long productId) {
        List<Rating> ratings = ratingRepository.findByProductId(productId);
        return ratings.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Tính trung bình điểm đánh giá
    public Double getAverageRating(Long productId) {
        return ratingRepository.getAverageRatingByProductId(productId).orElse(0.0);
    }

    // Đếm tổng số đánh giá cho sản phẩm
    public Long getTotalRatings(Long productId) {
        return ratingRepository.countByProductId(productId);
    }

    // Chuyển đổi Entity sang Response DTO
    private RatingResponse mapToResponse(Rating rating) {
        RatingResponse response = new RatingResponse();
        response.setId(rating.getId());
        response.setUserId(rating.getUser().getId());
        response.setProductId(rating.getProduct().getId());
        response.setScore(rating.getScore());
        response.setCreatedAt(rating.getCreatedAt());
        return response;
    }
}
