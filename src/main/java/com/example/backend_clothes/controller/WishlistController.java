package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.WishListRequest;
import com.example.backend_clothes.dto.response.ApiResponse;
import com.example.backend_clothes.dto.response.WishlistResponse;
import com.example.backend_clothes.service.WishlistService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WishlistController {

    WishlistService wishlistService;

    @PostMapping("/listWishlist")
    ApiResponse<List<WishlistResponse>> getWishlist(@RequestBody WishListRequest request) {
        return ApiResponse.<List<WishlistResponse>>builder()
                .result(wishlistService.getWishlist(request.getUserId()))
                .build();
    }

    @GetMapping("/exists")
    ApiResponse<Boolean> isProductInWishlist(@RequestBody WishListRequest request) {
        boolean exists = wishlistService.isProductInWishlist(request);
        return ApiResponse.<Boolean>builder()
                .result(exists)
                .build();
    }


    @PostMapping("/add")
    ApiResponse<WishlistResponse> addProductToWishlist(@RequestBody WishListRequest request) {
        return ApiResponse.<WishlistResponse>builder()
                .result(wishlistService.addProductToWishlist(request))
                .build();
    }

    @DeleteMapping
    ApiResponse<String> removeProductFromWishlist(
            @RequestParam Long userId, @RequestParam Long productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
        return ApiResponse.<String>builder()
                .result("wishlist removed")
                .build();
    }

}
