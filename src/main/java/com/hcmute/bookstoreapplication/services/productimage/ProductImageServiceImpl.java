package com.hcmute.bookstoreapplication.services.productimage;

import com.hcmute.bookstoreapplication.dtos.response.CloudinaryUploadResponse;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.entities.ProductImage;
import com.hcmute.bookstoreapplication.repositories.ProductImageRepository;
import com.hcmute.bookstoreapplication.services.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    @Override
    public ProductImage upload(byte[] data, Product product, Boolean isThumbnail) {
        try {
            CloudinaryUploadResponse cloudinaryUploadResponse = cloudinaryService.upload(data);
            ProductImage image = new ProductImage();
            image.setProduct(product);
            image.setThumbnail(isThumbnail);
            image.setPath(cloudinaryUploadResponse.getUrl());
            return image;
        }catch (IOException e){
            throw new RuntimeException("IOException occurred when upload data to Cloudinary service ("+ e.getMessage() + ")");
        }
    }
}
