package com.example.backend_clothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Setter
@Getter
@Entity

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String image;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Products> products;



}
