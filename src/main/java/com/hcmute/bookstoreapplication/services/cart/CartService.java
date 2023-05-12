package com.hcmute.bookstoreapplication.services.cart;

import com.hcmute.bookstoreapplication.dtos.CartDTO;
import com.hcmute.bookstoreapplication.dtos.request.ItemRequestDTO;
import com.hcmute.bookstoreapplication.dtos.response.BaseResponse;

import java.util.List;

public interface CartService {
    CartDTO getCart(Integer userId);
    BaseResponse createItem(ItemRequestDTO itemRequestDTO);
}
