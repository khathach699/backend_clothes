package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.LoginRequest;
import com.example.backend_clothes.dto.response.LoginResponse;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authService.login(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(loginResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(null, null, null, e.getMessage()));
        }
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return authService.getUserByUsername(username);
    }
}
