package com.hcmute.bookstoreapplication.controllers;

import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.dtos.ProductDetailDTO;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.services.product.ProductService;
import com.hcmute.bookstoreapplication.services.product_detail.ProductDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    public ProductController(ProductService productService, ProductDetailService productDetailService) {
        this.productService = productService;
        this.productDetailService = productDetailService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
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
    public ResponseEntity<List<ProductDTO>> getFilterProduct(@RequestParam("price") String price, @RequestParam("pulisher") String nxb){
        return new ResponseEntity<>(productService.filterProducts(price,nxb),HttpStatus.OK);
    }
}
