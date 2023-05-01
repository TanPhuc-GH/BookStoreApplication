package com.hcmute.bookstoreapplication.services.productimage;


import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.entities.ProductImage;

public interface ProductImageService {
    ProductImage upload(byte[] data, Product product, Boolean isThumbnail);
}
