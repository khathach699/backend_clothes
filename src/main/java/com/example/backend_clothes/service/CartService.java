package com.example.backend_clothes.service;

import com.example.backend_clothes.entity.Cart;
import com.example.backend_clothes.entity.ProductColorSize;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.enums.ErrorCode;
import com.example.backend_clothes.exception.AppException;
import com.example.backend_clothes.repository.CartRepository;
import com.example.backend_clothes.repository.ProductColorSizeRepository;
import com.example.backend_clothes.repository.ProductRepository;
import com.example.backend_clothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductColorSizeRepository productColorSizeRepository;

    public Cart addProductToCart(Long userId, Long productId, Long colorId, Long sizeId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        ProductColorSize productColorSize = productColorSizeRepository.findByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        if (productColorSize == null) {
            throw new AppException(ErrorCode.COLOR_END_SIZE_EXCEEDED);
        }

        Cart cart = cartRepository.findByUserAndProductAndProductColorSize(user, product, productColorSize);
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + quantity);
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setProductColorSize(productColorSize);
            cart.setQuantity(quantity);
            cart.setAddedAt(LocalDateTime.now());
        }

        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new AppException(ErrorCode.CAR_NOT_FOUND);
        }
        cartRepository.deleteById(cartId);
    }

    public List<Cart> getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return cartRepository.findByUser(user);
    }
}