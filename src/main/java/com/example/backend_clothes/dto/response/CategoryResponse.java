package com.example.backend_clothes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private  Long id;
    private String name;
    private String image;
    private List<ProductResponse> products;

}
