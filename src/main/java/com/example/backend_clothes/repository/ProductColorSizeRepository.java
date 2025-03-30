package com.example.backend_clothes.repository;

import com.example.backend_clothes.entity.ProductColorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorSizeRepository extends JpaRepository<ProductColorSize, Long> {

    // Custom query to find a ProductColorSize by productId, colorId, and sizeId
    @Query("SELECT pcs FROM ProductColorSize pcs WHERE pcs.product.id = :productId AND pcs.color.id = :colorId AND pcs.size.id = :sizeId")
    ProductColorSize findByProductIdAndColorIdAndSizeId(@Param("productId") Long productId,
                                                        @Param("colorId") Long colorId,
                                                        @Param("sizeId") Long sizeId);


}
