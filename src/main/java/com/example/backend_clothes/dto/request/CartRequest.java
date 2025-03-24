package com.example.backend_clothes.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {
    Long userId;
    Long productId;
    Long colorId;
    Long sizeId;
    int quantity;

}
