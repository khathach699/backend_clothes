package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.request.CommentRequest;
import com.example.backend_clothes.dto.response.CommentResponse;
import com.example.backend_clothes.entity.Comment;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.repository.CommentRepository;
import com.example.backend_clothes.repository.ProductRepository;
import com.example.backend_clothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // Lấy danh sách bình luận theo ID sản phẩm
    public List<CommentResponse> getCommentsByProductId(Long productId) {
        List<Comment> comments = commentRepository.findByProductId(productId);

        if (comments.isEmpty()) {
            throw new RuntimeException("No comments found for this product.");
        }

        // Tạo một tập hợp để theo dõi các bình luận đã xử lý
        Set<Long> processedIds = new HashSet<>();
        return comments.stream()
                .map(comment -> mapToResponse(comment, 2, processedIds)) // Truyền processedIds
                .filter(response -> response != null) // Lọc các phản hồi null
                .collect(Collectors.toList());
    }

    // Thêm mới bình luận
    public CommentResponse addComment(CommentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));
        Products product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found."));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setProduct(product);
        comment.setContent(request.getContent());
        comment.setTimestamp(LocalDateTime.now());

        if (request.getParentCommentId() != null) {
            Comment parentComment = commentRepository.findById(request.getParentCommentId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found."));
            comment.setParentComment(parentComment);
        }

        return mapToResponse(commentRepository.save(comment), 2, new HashSet<>()); // Giới hạn đệ quy
    }

    // Cập nhật nội dung bình luận
    public CommentResponse updateComment(CommentRequest request) {
        Comment comment = commentRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found."));

        // Kiểm tra xem người dùng hiện tại có phải là chủ sở hữu của bình luận
        if (!comment.getUser().getId().equals(request.getUserId())) {
            throw new RuntimeException("You are not authorized to update this comment.");
        }

        comment.setContent(request.getContent());
        comment.setTimestamp(LocalDateTime.now()); // Cập nhật thời gian chỉnh sửa
        return mapToResponse(commentRepository.save(comment), 2, new HashSet<>()); // Giới hạn độ sâu
    }



    // Xóa bình luận
    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found."));

        // Kiểm tra xem người dùng hiện tại có phải là chủ sở hữu của bình luận
        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this comment.");
        }

        commentRepository.deleteById(id);
    }

    // Hàm ánh xạ từ Comment sang CommentResponse DTO (giới hạn đệ quy)
    private CommentResponse mapToResponse(Comment comment, int depth, Set<Long> processedIds) {
        if (processedIds.contains(comment.getId())) {
            return null; // Tránh lặp
        }
        processedIds.add(comment.getId()); // Đánh dấu là đã xử lý

        return CommentResponse.builder()
                .id(comment.getId())
                .productId(comment.getProduct().getId())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .content(comment.getContent())
                .timestamp(comment.getTimestamp())
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .replies(depth > 0 && comment.getReplies() != null
                        ? comment.getReplies().stream()
                        .map(reply -> mapToResponse(reply, depth - 1, processedIds)) // Giảm depth
                        .filter(r -> r != null) // Lọc các phản hồi null
                        .collect(Collectors.toList())
                        : null)
                .build();
    }
}
