package com.example.backend_clothes.dto.response;

import com.example.backend_clothes.entity.Wishlist;

public class WishlistResponse {
    private Long id;
    private String productName;
    private Double productPrice;
    private String productImage;
    private String productDescription;
    private String categoryName; // Example of additional data you might want

    // Constructor to initialize WishlistResponse from Wishlist entity
    public WishlistResponse(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.productName = wishlist.getProduct().getName();
        this.productPrice = wishlist.getProduct().getPrice();
        this.productImage = wishlist.getProduct().getImage();
        this.productDescription = wishlist.getProduct().getDescription();
        this.categoryName = wishlist.getProduct().getCategory().getName(); // Assuming you want category details
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
