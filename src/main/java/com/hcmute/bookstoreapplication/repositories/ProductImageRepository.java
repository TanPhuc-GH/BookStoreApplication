package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

}
