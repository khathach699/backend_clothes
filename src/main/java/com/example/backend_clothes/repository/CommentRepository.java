package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(Long productId);
    List<Comment> findByParentCommentId(Long parentCommentId);
}
