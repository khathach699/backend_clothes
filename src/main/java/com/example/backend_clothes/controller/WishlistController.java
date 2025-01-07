package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.response.WishlistResponse;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get the user's wishlist
    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistResponse>> getWishlist(@PathVariable Long userId) {
        List<WishlistResponse> wishlist = wishlistService.getWishlist(userId);
        return ResponseEntity.ok(wishlist);
    }


    // Add a product to the wishlist
    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<Void> addProductToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.ok().build();
    }

    // Remove a product from the wishlist
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
        return ResponseEntity.ok().build();
    }
}
