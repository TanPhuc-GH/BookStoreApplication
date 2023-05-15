package com.hcmute.bookstoreapplication.dtos.request;

import com.hcmute.bookstoreapplication.dtos.ItemDTO;
import com.hcmute.bookstoreapplication.dtos.ItemDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestBuyNowDTO {

    private Integer userId;

    private String userName;

    private String address;

    private String phone;

    private ItemDetailDTO itemDetail;

    private String paymentMethod;

    private Float totalPrice;

}
