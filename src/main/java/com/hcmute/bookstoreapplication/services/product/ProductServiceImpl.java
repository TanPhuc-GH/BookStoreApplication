package com.hcmute.bookstoreapplication.services.product;

import com.cloudinary.utils.StringUtils;
import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> searchProducts(String query) {
        List<ProductDTO> products = productRepository.searchProducts(query);
        return products;
    }

    @Override
    public List<ProductDTO> filterProducts(String price, String nxb) {
        Specification<Product> spec = Specification.where(null);
        Sort sort;
        if (price.equalsIgnoreCase("low to high")) {
            sort = Sort.by("price").ascending();
        } else {
            sort = Sort.by("price").descending();
        }
        //root.get("publisher") là một cột trong CSDL
        if(!StringUtils.isEmpty(nxb)){
            spec = spec.and((root, cq, cb) -> cb.like(root.get("publisher"), "%" + nxb + "%"));
        }
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productRepository.findAll(spec,sort);
        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getPopularBook() {
        List<Product> products = productRepository.findAll();
        Collections.shuffle(products);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(int i = 1;i<=3;i++){
            Random random = new Random();
            int randomInt = random.nextInt(products.size());
            ProductDTO productDTO = new ProductDTO(products.get(randomInt));
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
