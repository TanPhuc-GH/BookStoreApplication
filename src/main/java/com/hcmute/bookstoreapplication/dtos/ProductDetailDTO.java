package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.entities.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private Integer id;
    private String productName;
    private String description;
    private Integer quantity;
    private String publisher;
    private Float price;
    private Date pulicationDate;
    private List<String> imagesProduct;
//    private String categories;
//    private String author;
    public ProductDetailDTO(Product product){
        this.setId(product.getId());
        this.setProductName(product.getProductName());
        this.setDescription(product.getDescription());
        this.setQuantity(product.getQuantity());
        this.setPublisher(product.getPublisher());
        this.setPrice(product.getPrice());
        this.setPulicationDate(product.getPublicationDate());
        List<String> imageList = new ArrayList<>();
        for(ProductImage productImage: product.getProductImages()) {
            String productImageSigle = productImage.getPath();
            imageList.add(productImageSigle);
        }
        this.setImagesProduct(imageList);
//        this.setCategories(product.getCategories().getName());
//        this.setAuthor(product.getAuthor().getAuthorName());

    }
}
