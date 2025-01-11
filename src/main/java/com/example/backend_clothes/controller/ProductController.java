package com.example.backend_clothes.controller;
import com.example.backend_clothes.dto.response.ProductResponse;
import com.example.backend_clothes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")  // This is the base path for product-related routes
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAlls")  // Path to retrieve all products
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/sortedByPrice")
    public List<ProductResponse> getProductsSortedByPrice() {
        // Call the service method to get products sorted by price descending
        return productService.getProductsByPriceDesc();
    }

    @GetMapping("/sortedByPriceAsc")
    public List<ProductResponse> getProductsSortedByPriceAsc() {
        // Call the service method to get products sorted by price ascending
        return productService.getProductsByPriceAsc();
    }

    @GetMapping("/most-purchased")
    public List<ProductResponse> getMostPurchasedProducts() {
        return productService.getMostPurchasedProducts();
    }
//
//    @GetMapping("/highest-rated")
//    public ProductResponse getHighestRatedProduct() {
//        return productService.getHighestRatedProduct();
//    }
}