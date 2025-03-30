package com.example.backend_clothes.service;

import com.example.backend_clothes.entity.Cart;
import com.example.backend_clothes.entity.ProductColorSize;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.enums.ErrorCode;
import com.example.backend_clothes.exception.AppException;
import com.example.backend_clothes.mapper.CartMapper;
import com.example.backend_clothes.dto.response.CartResponse;
import com.example.backend_clothes.repository.CartRepository;
import com.example.backend_clothes.repository.ProductColorSizeRepository;
import com.example.backend_clothes.repository.ProductRepository;
import com.example.backend_clothes.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductColorSizeRepository productColorSizeRepository;
    private final CartMapper cartMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Thêm sản phẩm vào giỏ hàng
     */
    public Cart addProductToCart(Long userId, Long productId, Long colorId, Long sizeId, int quantity) {
        // Tìm thông tin người dùng
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Tìm thông tin sản phẩm
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        // Tìm thông tin màu sắc và kích thước của sản phẩm
        ProductColorSize productColorSize = productColorSizeRepository
                .findByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        if (productColorSize == null) {
            throw new AppException(ErrorCode.COLOR_END_SIZE_EXCEEDED);
        }

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Cart cart = cartRepository.findByUserAndProductAndProductColorSize(user, product, productColorSize);
        if (cart != null) {
            // Nếu đã có, tăng số lượng
            cart.setQuantity(cart.getQuantity() + quantity);
        } else {
            // Nếu chưa có, tạo mới
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setProductColorSize(productColorSize);
            cart.setQuantity(quantity);
            cart.setAddedAt(LocalDateTime.now());
        }

        // Lưu giỏ hàng vào cơ sở dữ liệu
        Cart savedCart = cartRepository.save(cart);
        // Cập nhật dữ liệu trong Redis
        updateCartInRedis(userId);
        return savedCart;
    }

    /**
     * Xóa sản phẩm khỏi giỏ hàng
     */
    public void removeProductFromCart(Long cartId) {
        // Tìm giỏ hàng theo ID
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        Long userId = cart.getUser().getId();
        // Xóa khỏi cơ sở dữ liệu
        cartRepository.deleteById(cartId);
        // Cập nhật dữ liệu trong Redis
        updateCartInRedis(userId);
    }

    /**
     * Lấy danh sách giỏ hàng theo userId
     */
    public List<CartResponse> getCartByUserId(Long userId) {
        String key = "cart:user:" + userId;

        // Kiểm tra dữ liệu trong Redis trước
        Object cartData = redisTemplate.opsForValue().get(key);
        if (cartData != null) {
            try {
                // Nếu có, deserialize dữ liệu từ Redis
                return objectMapper.readValue(cartData.toString(), new TypeReference<List<CartResponse>>() {});
            } catch (JsonProcessingException e) {
                log.error("Lỗi khi deserialize dữ liệu giỏ hàng từ Redis", e);
            }
        }

        // Nếu không có trong Redis, lấy từ cơ sở dữ liệu
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        List<Cart> carts = cartRepository.findByUser(user);
        List<CartResponse> cartResponses = cartMapper.toCartResponseList(carts);

        // Lưu dữ liệu vào Redis với TTL 1 giờ
        saveCartToRedis(userId, cartResponses);

        return cartResponses;
    }

    /**
     * Lưu dữ liệu giỏ hàng vào Redis
     */
    private void saveCartToRedis(Long userId, List<CartResponse> cartResponses) {
        String key = "cart:user:" + userId;
        try {
            // Serialize dữ liệu thành JSON
            String cartData = objectMapper.writeValueAsString(cartResponses);
            // Lưu vào Redis với TTL 1 giờ
            redisTemplate.opsForValue().set(key, cartData);
            redisTemplate.expire(key, 1, TimeUnit.HOURS);
        } catch (JsonProcessingException e) {
            log.error("Lỗi khi serialize dữ liệu giỏ hàng vào Redis", e);
        }
    }

    /**
     * Cập nhật dữ liệu giỏ hàng trong Redis
     */
    private void updateCartInRedis(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        List<Cart> carts = cartRepository.findByUser(user);
        List<CartResponse> cartResponses = cartMapper.toCartResponseList(carts);
        saveCartToRedis(userId, cartResponses);
    }
}