package com.example.backend_clothes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "Username can not blank")
    @Size(min = 3, max = 50, message = "User must be between 3 and 50 characters")
    String username;
    @NotBlank(message = "Password can not blank")
    @Size(min = 3, max = 50, message = "Password must be between 3 and 50 characters")
    String password;
}
