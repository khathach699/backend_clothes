package com.example.backend_clothes.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishListRequest {
    Long userId;
    Long productId;
}
