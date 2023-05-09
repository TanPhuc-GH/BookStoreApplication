package com.hcmute.bookstoreapplication.controllers;

import com.hcmute.bookstoreapplication.entities.Item;
import com.hcmute.bookstoreapplication.services.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(Integer id,
                                           Integer quantity){
        return new ResponseEntity<>(cartService.createItem(id, quantity), HttpStatus.OK);
    }
}