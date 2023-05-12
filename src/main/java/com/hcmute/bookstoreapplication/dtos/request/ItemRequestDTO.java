package com.hcmute.bookstoreapplication.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    private Integer product_id;
    private Integer user_id;
    private Integer quantity;
}
