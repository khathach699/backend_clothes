package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.response.LoginResponse;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(String usernameOrEmail, String password) {
        // Tìm người dùng trong cơ sở dữ liệu
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Kiểm tra mật khẩu
            if (user.getPassword().equals(password)) {
                return new LoginResponse(user.getId(), user.getUsername(), user.getEmail(), "Login successful");
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
