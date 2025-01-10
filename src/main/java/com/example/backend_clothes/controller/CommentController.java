package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.CommentRequest;
import com.example.backend_clothes.dto.response.CommentResponse;
import com.example.backend_clothes.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Get comments for a specific product by its ID using query parameter
    @GetMapping("/list")
    public ResponseEntity<List<CommentResponse>> getComments(@RequestParam Long productId) {
        return ResponseEntity.ok(commentService.getCommentsByProductId(productId));
    }

    // Add a new comment
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(request));
    }

    // Update an existing comment
    // Cập nhật bình luận
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,
                                                         @RequestParam Long userId,
                                                         @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(id, userId, request.getContent()));
    }


    // Delete a comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.noContent().build();
    }
}
