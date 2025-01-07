package com.example.backend_clothes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference  // Use @JsonBackReference here to break the circular reference
    Products product;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    Color color;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    Size size;

    @Column(nullable = false)
    int quantity;

    public String getColorName() {
        return color != null ? color.getName() : null;
    }

    public String getSizeName() {
        return size != null ? size.getName() : null;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
