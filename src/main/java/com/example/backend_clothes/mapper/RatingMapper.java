package com.example.backend_clothes.mapper;


import com.example.backend_clothes.dto.request.RatingRequest;
import com.example.backend_clothes.dto.response.RatingResponse;
import com.example.backend_clothes.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    Rating toRating(RatingRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    RatingResponse toRatingResponse(Rating rating);

    @Mapping(source = "score", target = "score")
    void updateRating(@MappingTarget Rating rating, RatingRequest request);
}
