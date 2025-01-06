package com.example.backend_clothes.mapper;

import com.example.backend_clothes.dto.response.ProductColorSizeResponse;
import com.example.backend_clothes.dto.response.ProductResponse;
import com.example.backend_clothes.entity.ProductColorSize;
import com.example.backend_clothes.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productColorSizes", target = "colorSizes")
    ProductResponse productToProductResponse(Products product);

    List<ProductResponse> productsToProductResponses(List<Products> products);

    @Mapping(source = "color.name", target = "colorName")
    @Mapping(source = "size.name", target = "sizeName")
    ProductColorSizeResponse productColorSizeToProductColorSizeResponse(ProductColorSize productColorSize);
}

