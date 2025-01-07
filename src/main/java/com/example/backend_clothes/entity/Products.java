package com.example.backend_clothes.entity;

import com.example.backend_clothes.dto.response.ProductColorSizeResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    String name;
    Double price;
    String description;
    String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference  // Use @JsonManagedReference here to allow serialization
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference  // @JsonManagedReference for this side of the relationship
    private List<ProductColorSize> productColorSizes = new ArrayList<>();

    public List<ProductColorSize> getColorSizes() {
        return productColorSizes;
    }
}
