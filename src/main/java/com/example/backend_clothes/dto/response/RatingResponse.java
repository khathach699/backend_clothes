package com.example.backend_clothes.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingResponse {
    Long id;
    Long userId;
    Long productId;
    int score;
    LocalDateTime createdAt; // Thời gian tạo
}
