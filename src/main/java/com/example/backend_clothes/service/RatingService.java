package com.example.backend_clothes.service;

import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.entity.Rating;
import com.example.backend_clothes.entity.User;
import com.example.backend_clothes.dto.request.RatingRequest;
import com.example.backend_clothes.dto.response.RatingResponse;
import com.example.backend_clothes.enums.ErrorCode;
import com.example.backend_clothes.exception.AppException;
import com.example.backend_clothes.mapper.RatingMapper;
import com.example.backend_clothes.repository.OrdersRepository;
import com.example.backend_clothes.repository.RatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingService {

     RatingRepository ratingRepository;
     OrdersRepository ordersRepository;
     RatingMapper ratingMapper;


     public  RatingResponse addRating(RatingRequest request){
         log.info("Adding rating for user {} on product {}", request.getUserId(), request.getProductId());
         if(!ordersRepository.hasUserPurchasedProduct(request.getUserId(), request.getProductId())){
             throw new AppException(ErrorCode.NOT_PURCHASED);
         }
         if (request.getScore() < 1 || request.getScore() > 5) {
             throw new AppException(ErrorCode.RATING);
         }
         Rating rating = ratingMapper.toRating(request);
         Rating savedRating = ratingRepository.save(rating);
         log.info("Successfully added rating with ID {}", savedRating.getId());
         return ratingMapper.toRatingResponse(savedRating);
     }

    // Cập nhật đánh giá
    public RatingResponse updateRating(Long ratingId, RatingRequest request) {
        log.info("Updating rating with ID {}", ratingId);

        // Tìm đánh giá hiện tại
        Rating existingRating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new AppException(ErrorCode.RATING_NOT_FOUND));

        // Kiểm tra quyền sở hữu đánh giá
        if (!existingRating.getUser().getId().equals(request.getUserId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        // Cập nhật giá trị
        if (request.getScore() < 1 || request.getScore() > 5) {
            throw new AppException(ErrorCode.RATING);
        }

        ratingMapper.updateRating(existingRating, request);
        Rating updatedRating = ratingRepository.save(existingRating);

        log.info("Successfully updated rating with ID {}", updatedRating.getId());
        return ratingMapper.toRatingResponse(updatedRating);
    }
    // Lấy danh sách đánh giá theo sản phẩm
    public List<RatingResponse> getRatingsByProduct(Long productId) {
        log.info("Fetching ratings for product {}", productId);
        List<Rating> ratings = ratingRepository.findByProductId(productId);
        return ratings.stream().map(ratingMapper::toRatingResponse).toList();
    }

    // Tính trung bình điểm đánh giá
    public Double getAverageRating(Long productId) {
        log.info("Calculating average rating for product {}", productId);
        return ratingRepository.getAverageRatingByProductId(productId).orElse(0.0);
    }

    // Đếm tổng số đánh giá cho sản phẩm
    public Long getTotalRatings(Long productId) {
        log.info("Counting total ratings for product {}", productId);
        return ratingRepository.countByProductId(productId);
    }
}
