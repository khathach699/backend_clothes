package com.example.backend_clothes.enums;


public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Uncategorized error"),
    USER_EXISTED(1002, "User existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters"),
    USER_NOT_FOUND(1004, "User not found"),
    PRODUCT_NOT_FOUND(1005, "Product not found"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    RATING(1007, "Rating must be at least 5"),
    NOT_PURCHASED(1008, "Not purchased"),
    RATING_NOT_FOUND(1009, "Rating not found"),
    UNAUTHORIZED(1010, "Authentication "),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}