package com.example.backend_clothes.dto.response;

import com.example.backend_clothes.entity.Wishlist;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishlistResponse {
    // Getters and Setters
    Long id;
    String name;
    Double price;
    String image;
    String description;
    String categoryName;
    List<ProductColorSizeResponse> colorSizes;  // Add color and size combinations

//    // Constructor to initialize WishlistResponse from Wishlist entity
    public WishlistResponse(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.name = wishlist.getProduct().getName();
        this.price = wishlist.getProduct().getPrice();
        this.image = wishlist.getProduct().getImage();
        this.description = wishlist.getProduct().getDescription();
        this.categoryName = wishlist.getProduct().getCategory().getName();

        // Add logic to fetch color and size combinations
        this.colorSizes = wishlist.getProduct().getProductColorSizes().stream()
                .map(productColorSize -> {
                    ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
                    colorSizeResponse.setColorName(productColorSize.getColor().getName());
                    colorSizeResponse.setSizeName(productColorSize.getSize().getName());
                    colorSizeResponse.setQuantity(productColorSize.getQuantity());
                    return colorSizeResponse;
                })
                .collect(Collectors.toList());
    }


}
