package com.hcmute.bookstoreapplication.dtos.request;

import com.hcmute.bookstoreapplication.dtos.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestDTO {
    private Integer userId;

    private String userName;

    private String address;

    private String phone;

    private List<ItemDTO> itemDTOS;

    private String paymentMethod;

    private Float totalPrice;
}
