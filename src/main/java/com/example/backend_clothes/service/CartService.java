package com.example.backend_clothes.service;

import com.example.backend_clothes.entity.Cart;
import com.example.backend_clothes.entity.ProductColorSize;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.repository.CartRepository;
import com.example.backend_clothes.repository.ProductColorSizeRepository;
import com.example.backend_clothes.repository.ProductRepository;
import com.example.backend_clothes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorSizeRepository productColorSizeRepository; // Add repository to fetch ProductColorSize

    public Cart addProductToCart(Long userId, Long productId, Long colorId, Long sizeId, int quantity) {
        // Kiểm tra xem người dùng và sản phẩm có tồn tại không
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Products product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra xem sản phẩm với màu và kích thước đã có trong giỏ hàng chưa
        ProductColorSize productColorSize = productColorSizeRepository.findByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);

        if (productColorSize == null) {
            throw new RuntimeException("Product color/size combination not found");
        }

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Cart cart = cartRepository.findByUserAndProductAndProductColorSize(user, product, productColorSize);
        if (cart != null) {
            // Nếu sản phẩm đã có, tăng số lượng
            cart.setQuantity(cart.getQuantity() + quantity);
        } else {
            // Nếu chưa có, tạo mới mục giỏ hàng
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setProductColorSize(productColorSize); // Set the color/size combination
            cart.setQuantity(quantity);
            cart.setAddedAt(LocalDateTime.now());
        }

        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public List<Cart> getCartByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user);
    }
}
