package com.example.backend_clothes.dto.response;

import com.example.backend_clothes.entity.Wishlist;

public class WishlistResponse {
    private Long id;
    private String name;
    private Double price;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    private String description;
    private String categoryName; // Example of additional data you might want

    // Constructor to initialize WishlistResponse from Wishlist entity
    public WishlistResponse(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.name = wishlist.getProduct().getName();
        this.price = wishlist.getProduct().getPrice();
        this.image = wishlist.getProduct().getImage();
        this.description = wishlist.getProduct().getDescription();
        this.categoryName = wishlist.getProduct().getCategory().getName(); // Assuming you want category details
    }

    // Getters and Setters


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
