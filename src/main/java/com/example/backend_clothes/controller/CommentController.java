package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.CommentRequest;
import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.CommentResponse;
import com.example.backend_clothes.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;

    @GetMapping("/list/{productId}")
    ApiResponse<List<CommentResponse>> getComments(@PathVariable Long productId) {
        return ApiResponse.
                <List<CommentResponse>>builder()
                .result(commentService.getCommentsByProductId(productId))
                .build();
    }

    // Add a new comment
    @PostMapping
    ApiResponse<CommentResponse> addComment(@RequestBody CommentRequest request) {
        return ApiResponse.
                <CommentResponse>builder()
                .result(commentService.addComment(request))
                .build();
    }


    @PutMapping("/{id}")
    ApiResponse<CommentResponse> updateComment(@PathVariable Long id, @RequestParam Long userId, @RequestBody CommentRequest request) {
        return ApiResponse.
                <CommentResponse>builder()
                .result(commentService.updateComment(id, userId, request.getContent()))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteComment(@PathVariable Long id,
                                    @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ApiResponse.<Void>builder().build();
    }
}
