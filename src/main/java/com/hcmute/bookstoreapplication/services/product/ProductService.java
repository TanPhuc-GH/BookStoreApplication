package com.hcmute.bookstoreapplication.services.product;


import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();
    List<ProductDTO> searchProducts(String query);

    List<ProductDTO> filterProducts(String price, String nxb);
    
    List<ProductDTO> getPopularBook();
}
