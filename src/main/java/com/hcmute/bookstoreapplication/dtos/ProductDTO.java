package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.entities.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;

    private String productName;

    private Float price;

    private String imagePath;

    public ProductDTO(Product product){
        this.setId(product.getId());
        this.setProductName(product.getProductName());
        this.setPrice(product.getPrice());
        for (ProductImage productImage : product.getProductImages()){
            if(productImage.getThumbnail())
                this.setImagePath(productImage.getPath());
        }
    }
}
