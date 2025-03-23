package com.example.backend_clothes.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private int score;
    private LocalDateTime createdAt; // Thời gian tạo
}
