package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.RatingRequest;
import com.example.backend_clothes.dto.response.RatingResponse;
import com.example.backend_clothes.entity.Rating;
import com.example.backend_clothes.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // API thêm mới một đánh giá
    @PostMapping
    public ResponseEntity<RatingResponse> addRating(@RequestBody RatingRequest request) {
        try {
            RatingResponse savedRating = ratingService.addRating(request);
            return ResponseEntity.ok(savedRating);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Trả về lỗi nếu điểm không hợp lệ
        }
    }

    // API lấy danh sách đánh giá theo sản phẩm
    @GetMapping("/{productId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByProduct(@PathVariable Long productId) {
        List<RatingResponse> ratings = ratingService.getRatingsByProduct(productId);
        return ResponseEntity.ok(ratings);
    }

    // API tính điểm trung bình đánh giá
    @GetMapping("/average/{productId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long productId) {
        Double averageRating = ratingService.getAverageRating(productId);
        return ResponseEntity.ok(averageRating);
    }

    // API lấy tổng số đánh giá
    @GetMapping("/count/{productId}")
    public ResponseEntity<Long> getTotalRatings(@PathVariable Long productId) {
        Long totalRatings = ratingService.getTotalRatings(productId);
        return ResponseEntity.ok(totalRatings);
    }
}
