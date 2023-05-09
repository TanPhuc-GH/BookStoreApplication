package com.hcmute.bookstoreapplication.services.cart;

import com.hcmute.bookstoreapplication.entities.Item;

public interface CartService {
    Item createItem(Integer id, Integer quantity);
}
