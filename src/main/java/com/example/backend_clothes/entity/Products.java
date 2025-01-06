package com.example.backend_clothes.entity;

import com.example.backend_clothes.dto.response.ProductColorSizeResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Double price;


    String description;

    String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    List<ProductColorSize> productColorSizes = new ArrayList<>();

    public List<ProductColorSize> getColorSizes() {
        return productColorSizes;
    }


}

