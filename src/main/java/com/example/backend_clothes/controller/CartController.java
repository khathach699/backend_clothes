package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.CartRequest;
import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.CartResponse;
import com.example.backend_clothes.entity.Cart;
import com.example.backend_clothes.mapper.CartMapper;
import com.example.backend_clothes.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;
    CartMapper cartMapper;

    @PostMapping("/add")
    ApiResponse<CartResponse> addToCart(@RequestBody CartRequest cartRequest) {

        Cart cart = cartService.addProductToCart(
                cartRequest.getUserId(),
                cartRequest.getProductId(),
                cartRequest.getColorId(),
                cartRequest.getSizeId(),
                cartRequest.getQuantity()
        );
        return ApiResponse.<CartResponse>builder()
                .result(cartMapper.toCartResponse(cart))
                .build();

    }

    @DeleteMapping("/remove/{cartId}")
    ApiResponse<String> removeFromCart(@PathVariable Long cartId) {
        cartService.removeProductFromCart(cartId);
        return ApiResponse.<String>builder()
                .result("Product removed from cart")
                .build();

    }

    @GetMapping("/list/{userId}")
    ApiResponse<List<CartResponse>> getCart(@PathVariable Long userId) {

        List<Cart> carts = cartService.getCartByUserId(userId);
        List<CartResponse> responses = cartMapper.toCartResponseList(carts);
        return ApiResponse.<List<CartResponse>>builder()
                .result(responses)
                .build();
    }
}