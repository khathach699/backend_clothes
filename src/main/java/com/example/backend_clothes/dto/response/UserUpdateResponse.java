package com.example.backend_clothes.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {
    String userId;
    String username;
    String email;
    String password;
    String phone;
    String gender;
    LocalDate dateOfBirth;
}
