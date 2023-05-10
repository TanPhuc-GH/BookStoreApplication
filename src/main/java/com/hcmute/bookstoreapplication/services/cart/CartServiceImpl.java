package com.hcmute.bookstoreapplication.services.cart;

import com.hcmute.bookstoreapplication.dtos.ItemDTO;
import com.hcmute.bookstoreapplication.dtos.request.ItemRequestDTO;
import com.hcmute.bookstoreapplication.entities.Item;
import com.hcmute.bookstoreapplication.entities.Product;
import com.hcmute.bookstoreapplication.exceptions.NotFoundException;
import com.hcmute.bookstoreapplication.repositories.ItemRepository;
import com.hcmute.bookstoreapplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public ItemDTO createItem(ItemRequestDTO itemRequestDTO) {
        Optional<Product> product = productRepository.findById(itemRequestDTO.getId());
        if(!product.isPresent()){
            throw new NotFoundException(String.format("Product with id %d not found.", itemRequestDTO.getId()));
        }
        Item item = new Item();
        ItemDTO itemDTO = new ItemDTO();
        Integer quantityProduct = product.get().getQuantity();
        if(itemRequestDTO.getQuantity() <= quantityProduct) {
            item.setProduct(product.get());
            item.setItemName(product.get().getProductName());
            item.setPrice(product.get().getPrice());
            item.setQuantity(itemRequestDTO.getQuantity());
            itemRepository.save(item);
            itemDTO.setProduct_id(product.get().getId());
            itemDTO.setItemName(product.get().getProductName());
            itemDTO.setPrice(product.get().getPrice());
            itemDTO.setQuantity(itemRequestDTO.getQuantity());
            return itemDTO;

        }else {
            throw new RuntimeException("Quantity of product is larger than in warehouse");
        }
    }
}
