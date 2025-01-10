package com.example.backend_clothes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY) // FetchType.LAZY để tránh tải dữ liệu quá sớm
    @JsonManagedReference // Dùng @JsonIgnore để không tuần tự hóa danh sách sản phẩm
    private List<Products> products;
}

