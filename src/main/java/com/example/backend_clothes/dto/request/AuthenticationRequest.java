package com.example.backend_clothes.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


public record AuthenticationRequest(String email, String password) {
}
