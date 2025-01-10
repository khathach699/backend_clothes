package com.example.backend_clothes.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
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
    @JsonManagedReference  // This allows the Category object to be serialized
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference  // This allows the ProductColorSize objects to be serialized
    private List<ProductColorSize> productColorSizes = new ArrayList<>();

    public List<ProductColorSize> getColorSizes() {
        return productColorSizes;
    }
}
