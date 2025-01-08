package com.example.backend_clothes.dto.response;

import java.time.LocalDateTime;

public class CartResponse {
    private Long cartId;
    private String productName;
    private String colorName;
    private String sizeName;
    private int quantity;
    private Double price; // Price based on color and size
    private LocalDateTime addedAt;

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    private String productImage;

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CartResponse(Long cartId, String productName, String colorName, String sizeName, int quantity, Double price, LocalDateTime addedAt, String  productImage) {
        this.cartId = cartId;
        this.productName = productName;
        this.colorName = colorName;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.price = price;
        this.addedAt = addedAt;
        this.productImage = productImage;
    }
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }


}
