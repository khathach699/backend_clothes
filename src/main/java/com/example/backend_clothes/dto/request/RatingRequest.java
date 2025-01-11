package com.example.backend_clothes.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
    private Long userId;        // ID người dùng
    private Long productId;     // ID sản phẩm
    private int score;          // Điểm đánh giá (1-5)
}
