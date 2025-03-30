package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.RatingRequest;
import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.RatingResponse;
import com.example.backend_clothes.service.RatingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingController {


    RatingService ratingService;

    // API thêm mới một đánh giá
    @PostMapping
    ApiResponse<RatingResponse> addRating(@RequestBody RatingRequest request) {
        return ApiResponse.<RatingResponse>builder()
                .result(ratingService.addRating(request))
                .build();
    }

    // API lấy danh sách đánh giá theo sản phẩm
    @GetMapping("/{productId}")
    ApiResponse<List<RatingResponse>> getRatingsByProduct(@PathVariable Long productId) {
        return ApiResponse.<List<RatingResponse>>builder()
                .result(ratingService.getRatingsByProduct(productId))
                .build();
    }

    // API tính điểm trung bình đánh giá
    @GetMapping("/average/{productId}")
    ApiResponse<Double> getAverageRating(@PathVariable Long productId) {
        return ApiResponse.
                <Double>builder()
                .result(ratingService.getAverageRating(productId))
                .build();
    }

    // API lấy tổng số đánh giá
    @GetMapping("/count/{productId}")
    ApiResponse<Long> getTotalRatings(@PathVariable Long productId) {
        return ApiResponse.<Long>builder()
                .result(ratingService.getTotalRatings(productId))
                .build();
    }
}
