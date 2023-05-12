package com.hcmute.bookstoreapplication.controllers;

import com.hcmute.bookstoreapplication.dtos.CartDTO;
import com.hcmute.bookstoreapplication.dtos.request.ItemRequestDTO;
import com.hcmute.bookstoreapplication.dtos.response.BaseResponse;
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

    @GetMapping("/items")
    public ResponseEntity<CartDTO> getCart(@RequestParam Integer userId){
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createItem(@RequestBody ItemRequestDTO itemRequestDTO){
        try {
            return ResponseEntity.ok(cartService.createItem(itemRequestDTO));
        }catch (RuntimeException e){
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new BaseResponse(false, e.getMessage()));
        }
    }

}
