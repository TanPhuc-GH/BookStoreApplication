package com.hcmute.bookstoreapplication.services.cart;

import com.hcmute.bookstoreapplication.dtos.CartDTO;
import com.hcmute.bookstoreapplication.dtos.CheckoutDTO;
import com.hcmute.bookstoreapplication.dtos.request.CheckoutRequestBuyNowDTO;
import com.hcmute.bookstoreapplication.dtos.request.CheckoutRequestDTO;
import com.hcmute.bookstoreapplication.dtos.request.ItemRequestDTO;
import com.hcmute.bookstoreapplication.dtos.response.BaseResponse;

public interface CartService {
    CartDTO getCart(Integer userId);
    BaseResponse buyNow(CheckoutRequestBuyNowDTO checkoutBuyNowRequestDTO);
    CheckoutDTO getCheckoutInfo(Integer userId);
    BaseResponse checkout(CheckoutRequestDTO checkoutRequestDTO);
    BaseResponse createItem(ItemRequestDTO itemRequestDTO);
}
