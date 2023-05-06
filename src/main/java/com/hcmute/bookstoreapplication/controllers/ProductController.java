package com.hcmute.bookstoreapplication.controllers;

import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.dtos.ProductDetailDTO;
import com.hcmute.bookstoreapplication.dtos.response.CloudinaryUploadResponse;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.services.cloudinary.CloudinaryService;
import com.hcmute.bookstoreapplication.services.product.ProductService;
import com.hcmute.bookstoreapplication.services.product_detail.ProductDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;
    private final CloudinaryService cloudinaryService;

    public ProductController(ProductService productService, ProductDetailService productDetailService, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.productDetailService = productDetailService;
        this.cloudinaryService = cloudinaryService;
    }

    @CrossOrigin(origins = "http://192.168.2.114:8080/api/product/")
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestPart ProductDetailDTO productDetailDTO,
                                          @RequestPart("thumbnail") MultipartFile thumbnail,
                                          @RequestPart("images") List<MultipartFile> images){
        try{
            Product product = productService.addProduct(productDetailDTO,thumbnail, images);
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding product",e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getDetailProduct(@PathVariable Integer id){
        return new ResponseEntity<>(productDetailService.getProductDetail(id),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> getSearchProduct(@RequestParam("keyword") String query){
        return new ResponseEntity<>(productService.searchProducts(query),HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> getFilterProduct(@RequestParam("price") String price, @RequestParam("publisher") String nxb){
        return new ResponseEntity<>(productService.filterProducts(price,nxb),HttpStatus.OK);
    }
}
