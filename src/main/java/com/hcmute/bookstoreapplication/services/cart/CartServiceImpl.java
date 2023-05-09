package com.hcmute.bookstoreapplication.services.cart;

import com.hcmute.bookstoreapplication.entities.Item;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.exceptions.NotFoundException;
import com.hcmute.bookstoreapplication.repositories.ItemRepository;
import com.hcmute.bookstoreapplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item createItem(Integer id, Integer quantity) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new NotFoundException(String.format("Not find product has id: ",id));
        }

        Item item = new Item();
        Integer quantityProduct = product.get().getQuantity();

        if(quantity <= quantityProduct) {
            item.setProduct(product.get());
            item.setItemName(product.get().getProductName());
            item.setPrice(product.get().getPrice());
            item.setQuantity(quantity);
            itemRepository.save(item);
        }else {
            throw new RuntimeException("Quantity of product is larger than in warehouse");
        }
        return item;
    }
}
