package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.response.WishlistResponse;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.entity.Wishlist;
import com.example.backend_clothes.repository.ProductRepository;
import com.example.backend_clothes.repository.UserRepository;
import com.example.backend_clothes.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Add Product to Wishlist
    public void addProductToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Products product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Wishlist existingWishlistItem = wishlistRepository.findByUserIdAndProductId(userId, productId).orElse(null);
        if (existingWishlistItem != null) {
            throw new RuntimeException("Wishlist item already exists");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlist.setAddedAt(LocalDateTime.now());
        wishlistRepository.save(wishlist);
    }

    // Remove Product from Wishlist
    @Transactional
    public void removeProductFromWishlist(Long userId, Long productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }

    // Get Wishlist as WishlistResponse list
    public List<WishlistResponse> getWishlist(Long userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserId(userId);
        List<WishlistResponse> wishlistResponses = new ArrayList<>();

        for (Wishlist item : wishlistItems) {
            wishlistResponses.add(new WishlistResponse(item));  // Convert to WishlistResponse
        }
        return wishlistResponses;
    }
}

