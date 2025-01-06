package com.example.backend_clothes.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductColorSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Products product;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    Color color;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    Size size;

    @Column(nullable = false)
    int quantity; // Quantity for each combination (product, color, size)

    public String getColorName() {
        return color != null ? color.getName() : null; // Access color's name field
    }

    public String getSizeName() {
        return size != null ? size.getName() : null; // Access size's name field
    }

    public Integer getQuantity() {
        return quantity;
    }
}
