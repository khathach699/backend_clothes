package com.example.backend_clothes.mapper;

import com.example.backend_clothes.dto.request.CartRequest;
import com.example.backend_clothes.dto.response.CartResponse;
import com.example.backend_clothes.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "id", target = "cartId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "productColorSize.colorName", target = "colorName")
    @Mapping(source = "productColorSize.sizeName", target = "sizeName")
    @Mapping(source = "productColorSize.product.price", target = "price")
//    @Mapping(source = "product.image", target = "product.image")
    CartResponse toCartResponse(Cart cart);
    List<CartResponse> toCartResponseList(List<Cart> carts);
}