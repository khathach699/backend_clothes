package com.example.backend_clothes.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
    Long id;
    Long productId;
    Long userId;
    String username;
    String content;
    LocalDateTime timestamp;
    Long parentCommentId;
    List<CommentResponse> replies;
}