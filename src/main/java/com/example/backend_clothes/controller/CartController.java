package com.example.backend_clothes.controller;

import com.example.backend_clothes.dto.request.CartRequest;
import com.example.backend_clothes.dto.response.CartResponse;
import com.example.backend_clothes.entity.Cart;
import com.example.backend_clothes.entity.ProductColorSize;
import com.example.backend_clothes.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/cart")



public class CartController {

    @Autowired
    private CartService cartService;

    // API thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest cartRequest) {
        try {
            // Fetch product color size based on the cart request
            Cart cart = cartService.addProductToCart(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getColorId(), cartRequest.getSizeId(), cartRequest.getQuantity());
            ProductColorSize productColorSize = cart.getProductColorSize(); // Get color size details

            CartResponse cartResponse = new CartResponse(
                    cart.getId(),
                    cart.getProduct().getId(), // Lấy productId từ thực thể Product
                    cart.getProduct().getName(),
                    productColorSize.getColorName(),
                    productColorSize.getSizeName(),
                    cart.getQuantity(),
                    productColorSize.getProduct().getPrice(),
                    cart.getAddedAt(),
                    cart.getProduct().getImage()
            );


            return ResponseEntity.ok(cartResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // API xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId) {
        try {
            cartService.removeProductFromCart(cartId);
            return ResponseEntity.ok("Product removed from cart");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing product from cart");
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable Long userId) {
        try {
            List<Cart> carts = cartService.getCartByUserId(userId);
            List<CartResponse> cartResponses = new ArrayList<>();
            for (Cart cart : carts) {
                ProductColorSize productColorSize = cart.getProductColorSize();
                CartResponse cartResponse = new CartResponse(
                        cart.getId(),
                        cart.getProduct().getId(), // Lấy productId từ thực thể Product
                        cart.getProduct().getName(),
                        productColorSize.getColorName(),
                        productColorSize.getSizeName(),
                        cart.getQuantity(),
                        productColorSize.getProduct().getPrice(),
                        cart.getAddedAt(),
                        cart.getProduct().getImage()
                );
                cartResponses.add(cartResponse);

            }
            return ResponseEntity.ok(cartResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
