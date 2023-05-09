package com.hcmute.bookstoreapplication.services.product;

import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.hcmute.bookstoreapplication.dtos.ProductDTO;
import com.hcmute.bookstoreapplication.dtos.ProductDetailDTO;
import com.hcmute.bookstoreapplication.dtos.response.CloudinaryUploadResponse;
import com.hcmute.bookstoreapplication.entities.Author;
import com.hcmute.bookstoreapplication.entities.Categories;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.entities.ProductImage;
import com.hcmute.bookstoreapplication.repositories.ProductImageRepository;
import com.hcmute.bookstoreapplication.repositories.ProductRepository;
import com.hcmute.bookstoreapplication.services.cloudinary.CloudinaryService;
import com.hcmute.bookstoreapplication.services.productimage.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
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
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    ProductImageService productImageService;


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
    public Product addProduct(ProductDetailDTO productDetailDTO, MultipartFile thumbnail, List<MultipartFile> images){
        try{
            Product product = new Product();
            Categories categories = new Categories();
            Author author = new Author();

            categories.setId(productDetailDTO.getId());
            author.setId(productDetailDTO.getId());

            product.setProductName(productDetailDTO.getProductName());
            product.setQuantity(productDetailDTO.getQuantity());
            product.setDescription(productDetailDTO.getDescription());
            product.setPrice(productDetailDTO.getPrice());
            product.setPublisher(productDetailDTO.getPublisher());
            product.setPublicationDate(productDetailDTO.getPulicationDate());
            product.setCategories(categories);
            product.setAuthor(author);
//            productRepository.save(product);

            List<ProductImage> productImages = new ArrayList<>();
            ProductImage image = productImageService.upload(thumbnail.getBytes(),product,true);
            productImages.add(image);
            for (MultipartFile file : images) {
                ProductImage productImage = productImageService.upload(file.getBytes(), product, false);
                productImages.add(productImage);
            }

            productImageRepository.saveAll(productImages);

            return productRepository.save(product);

        }catch (IOException e){
            throw new RuntimeException("Error adding product");
        }
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
