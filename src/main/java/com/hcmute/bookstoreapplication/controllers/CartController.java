package com.hcmute.bookstoreapplication.controllers;

import com.hcmute.bookstoreapplication.dtos.ItemDTO;
import com.hcmute.bookstoreapplication.dtos.request.ItemRequestDTO;
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
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemRequestDTO itemRequestDTO){
        return new ResponseEntity<>(cartService.createItem(itemRequestDTO), HttpStatus.OK);
    }
}
