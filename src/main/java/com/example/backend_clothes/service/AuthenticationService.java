package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.request.AuthenticationRequest;
import com.example.backend_clothes.dto.response.AuthenticationResponse;
import com.example.backend_clothes.enums.ErrorCode;
import com.example.backend_clothes.exception.AppException;
import com.example.backend_clothes.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return new AuthenticationResponse(false, null); // Đăng nhập thất bại
        }
        return new AuthenticationResponse(true, user.getId());
    }
}


