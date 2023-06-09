package com.hcmute.bookstoreapplication.repositories;


import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE " +
            "p.productName LIKE CONCAT('%',:query,'%')" +
            "Or p.description LIKE CONCAT('%',:query,'%')")
    List<ProductDTO> searchProducts(String query);

    List<Product> findAll(Specification<Product> spec, Sort sort);
}
