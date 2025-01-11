package com.example.backend_clothes.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingResponse {
    private Long id;            // ID của đánh giá
    private Long userId;        // ID người dùng
    private Long productId;     // ID sản phẩm
    private int score;          // Điểm đánh giá
    private LocalDateTime createdAt; // Thời gian tạo
}
