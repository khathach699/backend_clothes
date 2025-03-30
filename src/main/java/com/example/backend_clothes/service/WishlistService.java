package com.example.backend_clothes.service;

import com.example.backend_clothes.dto.request.WishListRequest;
import com.example.backend_clothes.dto.response.WishlistResponse;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.entity.Wishlist;
import com.example.backend_clothes.enums.ErrorCode;
import com.example.backend_clothes.exception.AppException;
import com.example.backend_clothes.repository.ProductRepository;
import com.example.backend_clothes.repository.UserRepository;
import com.example.backend_clothes.repository.WishlistRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WishlistService {

     WishlistRepository wishlistRepository;
     ProductRepository productRepository;
     UserRepository userRepository;

    public WishlistResponse addProductToWishlist(WishListRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Products product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Wishlist existingWishlistItem = wishlistRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId()).orElse(null);
        if (existingWishlistItem != null) {
            throw new RuntimeException("Wishlist item already exists");
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlist.setAddedAt(LocalDateTime.now());
        wishlist = wishlistRepository.save(wishlist);
        return  new WishlistResponse(wishlist);
    }

    // Remove Product from Wishlist
    @Transactional
    public void removeProductFromWishlist(Long userId, Long productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId ,productId);
    }

    // Get Wishlist as WishlistResponse list
    public List<WishlistResponse> getWishlist(Long userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserId(userId);

        // Sử dụng constructor mới
        return wishlistItems.stream()
                .map(WishlistResponse::new)
                .collect(Collectors.toList());
    }

    public boolean isProductInWishlist(WishListRequest request) {
        return wishlistRepository.existsByUserIdAndProductId(request.getUserId(), request.getProductId());
    }
}

