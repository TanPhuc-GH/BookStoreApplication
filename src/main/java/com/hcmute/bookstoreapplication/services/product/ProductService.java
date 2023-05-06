package com.hcmute.bookstoreapplication.services.product;


import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.dtos.ProductDetailDTO;
import com.hcmute.bookstoreapplication.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();
    List<ProductDTO> searchProducts(String query);

    List<ProductDTO> filterProducts(String price, String nxb);

    Product addProduct(ProductDetailDTO productDetailDTO, MultipartFile thumbnail, List<MultipartFile> images);
    
    
}
