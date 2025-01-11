package com.example.backend_clothes.service;
import com.example.backend_clothes.dto.response.ProductColorSizeResponse;
import com.example.backend_clothes.dto.response.ProductResponse;
import com.example.backend_clothes.entity.OrderItem;
import com.example.backend_clothes.entity.Orders;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.repository.OrdersRepository;
import com.example.backend_clothes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private ProductMapper productMapper;

    @Autowired
    private OrdersRepository orderRepository;

    public List<ProductResponse> getAllProducts() {
        // Fetch tất cả sản phẩm cùng với các kết hợp màu sắc và kích thước
        List<Products> products = productRepository.findAllWithColorSizes(); // Truy vấn đã sửa
        return products.stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setImage(product.getImage());
            productResponse.setCategoryName(product.getCategory().getName());

            // Lấy các kết hợp màu sắc và kích thước
            List<ProductColorSizeResponse> colorSizes = product.getProductColorSizes().stream()
                    .map(productColorSize -> {
                        ProductColorSizeResponse colorSizeDTO = new ProductColorSizeResponse();
                        colorSizeDTO.setColorName(productColorSize.getColor().getName());
                        colorSizeDTO.setSizeName(productColorSize.getSize().getName());
                        colorSizeDTO.setQuantity(productColorSize.getQuantity());
                        return colorSizeDTO;
                    }).collect(Collectors.toList());

            productResponse.setColorSizes(colorSizes);
            return productResponse;
        }).collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByPriceDesc() {
        // Fetch all products sorted by price in descending order
        List<Products> products = productRepository.findAllByPriceDescending(); // Repository query for descending price
        return products.stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setImage(product.getImage());
            productResponse.setCategoryName(product.getCategory().getName());

            // Get color-size combinations
            List<ProductColorSizeResponse> colorSizes = product.getProductColorSizes().stream()
                    .map(productColorSize -> {
                        ProductColorSizeResponse colorSizeDTO = new ProductColorSizeResponse();
                        colorSizeDTO.setColorName(productColorSize.getColor().getName());
                        colorSizeDTO.setSizeName(productColorSize.getSize().getName());
                        colorSizeDTO.setQuantity(productColorSize.getQuantity());
                        return colorSizeDTO;
                    }).collect(Collectors.toList());

            productResponse.setColorSizes(colorSizes);
            return productResponse;
        }).collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByPriceAsc() {
        // Fetch all products sorted by price in ascending order
        List<Products> products = productRepository.findAllByPriceAscending(); // Repository query for ascending price
        return products.stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setImage(product.getImage());
            productResponse.setCategoryName(product.getCategory().getName());

            // Get color-size combinations
            List<ProductColorSizeResponse> colorSizes = product.getProductColorSizes().stream()
                    .map(productColorSize -> {
                        ProductColorSizeResponse colorSizeDTO = new ProductColorSizeResponse();
                        colorSizeDTO.setColorName(productColorSize.getColor().getName());
                        colorSizeDTO.setSizeName(productColorSize.getSize().getName());
                        colorSizeDTO.setQuantity(productColorSize.getQuantity());
                        return colorSizeDTO;
                    }).collect(Collectors.toList());

            productResponse.setColorSizes(colorSizes);
            return productResponse;
        }).collect(Collectors.toList());
    }

    public List<ProductResponse> getMostPurchasedProducts() {
        // Get all orders
        List<Orders> allOrders = orderRepository.findAll();

        // Create a map to store the total quantity sold for each product
        Map<Long, Integer> productQuantityMap = new HashMap<>();

        // Loop through each order and its items
        for (Orders order : allOrders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                Long productId = orderItem.getProduct().getId();
                int quantity = orderItem.getQuantity();

                // Update the quantity map
                productQuantityMap.put(productId, productQuantityMap.getOrDefault(productId, 0) + quantity);
            }
        }

        // Sort products by their total quantity in descending order and get the most purchased ones
        List<Long> sortedProductIds = productQuantityMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))  // Sort by quantity descending
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Fetch the products by the sorted product IDs
        List<Products> mostPurchasedProducts = productRepository.findAllById(sortedProductIds);

        // Map to response DTOs
        List<ProductResponse> productResponses = mostPurchasedProducts.stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setPrice(product.getPrice());
                    productResponse.setDescription(product.getDescription());
                    productResponse.setImage(product.getImage());
                    productResponse.setCategoryName(product.getCategory().getName());

                    // Get color-size combinations (add this part)
                    List<ProductColorSizeResponse> colorSizes = product.getProductColorSizes().stream()
                            .map(productColorSize -> {
                                ProductColorSizeResponse colorSizeDTO = new ProductColorSizeResponse();
                                colorSizeDTO.setColorName(productColorSize.getColor().getName());
                                colorSizeDTO.setSizeName(productColorSize.getSize().getName());
                                colorSizeDTO.setQuantity(productColorSize.getQuantity());
                                return colorSizeDTO;
                            }).collect(Collectors.toList());

                    // Set the color-size combinations to the product response
                    productResponse.setColorSizes(colorSizes);

                    return productResponse;
                })
                .collect(Collectors.toList());

        return productResponses;
    }


//
//
//    public ProductResponse getHighestRatedProduct() {
//        // Fetch the product with the highest rating (Assuming 'rating' is a field in Product)
//        Optional<Products> highestRatedProduct = productRepository.findTopByOrderByRatingDesc();
//
//        if (highestRatedProduct.isPresent()) {
//            Products product = highestRatedProduct.get();
//            return mapToProductResponse(product);
//        } else {
//            throw new RuntimeException("No products found");
//        }
//    }

    private ProductResponse mapToProductResponse(Products product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setCategoryName(product.getCategory().getName());
        // Add any other necessary mappings
        return productResponse;
    }

}


