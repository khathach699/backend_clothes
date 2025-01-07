package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository   extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);

}

