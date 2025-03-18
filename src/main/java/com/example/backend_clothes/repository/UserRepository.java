package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository   extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);
    Optional<User> findByEmail(String email);

}

