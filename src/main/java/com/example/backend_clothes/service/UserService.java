package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.request.UserCreateRequest;
import com.example.backend_clothes.dto.request.UserUpdateRequest;
import com.example.backend_clothes.dto.response.UserResponse;
import com.example.backend_clothes.dto.response.UserUpdateResponse;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.enums.ErrorCode;
import com.example.backend_clothes.exception.AppException;
import com.example.backend_clothes.mapper.UserMapper;
import com.example.backend_clothes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserUpdateResponse updateUser(String userId, UserUpdateRequest request) {
        Long id = Long.parseLong(userId);
        User user =  userRepository.findById( id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserUpdateResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(Long.parseLong(userId));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(
                userMapper::toUserResponse
        ).toList();
    }

    public UserResponse getUserById(String userId) {
        Long id = Long.parseLong(userId);
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        ));
    }
}
