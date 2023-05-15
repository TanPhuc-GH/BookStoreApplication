package com.hcmute.bookstoreapplication.services.product_detail;

import com.hcmute.bookstoreapplication.dtos.ProductDetailDTO;
import com.hcmute.bookstoreapplication.dtos.request.CheckoutRequestBuyNowDTO;
import com.hcmute.bookstoreapplication.dtos.response.BaseResponse;
import com.hcmute.bookstoreapplication.entities.*;
import com.hcmute.bookstoreapplication.exceptions.NotFoundException;
import com.hcmute.bookstoreapplication.repositories.OrderDetailRepository;
import com.hcmute.bookstoreapplication.repositories.OrderRepository;
import com.hcmute.bookstoreapplication.repositories.ProductRepository;
import com.hcmute.bookstoreapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDetailDTO getProductDetail(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new RuntimeException("Product not found with id: "+id);
        }
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(product.get());
        return productDetailDTO;
    }
}
