package com.example.backend_clothes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String image;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonBackReference  // Đảm bảo sử dụng @JsonBackReference ở đây để tránh vòng lặp
    private List<Products> products;
}

