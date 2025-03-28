package com.example.backend_clothes.service;
import com.example.backend_clothes.dto.response.ProductColorSizeResponse;
import com.example.backend_clothes.dto.response.ProductResponse;
import com.example.backend_clothes.entity.Products;
import com.example.backend_clothes.mapper.ProductMapper;
import com.example.backend_clothes.repository.ProductColorSizeRepository;
import com.example.backend_clothes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private ProductMapper productMapper;

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

}


