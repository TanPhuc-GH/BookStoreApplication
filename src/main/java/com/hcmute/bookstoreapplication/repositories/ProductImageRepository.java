package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

    @Query("select im " +
            "from ProductImage im " +
            "where im.product.id = :productId and im.thumbnail = true")
    ProductImage getThumbnail(@Param("productId") Integer productId);
}
