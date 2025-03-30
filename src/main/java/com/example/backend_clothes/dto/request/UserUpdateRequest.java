package com.example.backend_clothes.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "Username can not blank")
    @Size(min = 3, max = 50, message = "User must be between 3 and 50 characters")
    String username;
    @NotBlank(message = "Password can not blank")
    @Size(min = 3, max = 50, message = "Password must be between 3 and 50 characters")
    String password;
    @NotBlank(message = "phone can not blank")
    @Size(min =  10, max =  10, message = "phone have to 10 character")
    String phone;
    @NotBlank(message = "phone can not blank")
    String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;
}
