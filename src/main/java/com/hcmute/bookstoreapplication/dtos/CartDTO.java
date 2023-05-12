package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.Cart;
import com.hcmute.bookstoreapplication.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Integer id;

    private Integer userId;

    private List<ItemDTO> itemList;
    public CartDTO(Cart cart){
        this.setId(cart.getId());
        this.setUserId(cart.getUser().getId());
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item singleItem : cart.getItems()){
            ItemDTO itemDTO = new ItemDTO(singleItem);
            itemDTOS.add(itemDTO);
        }
        this.setItemList(itemDTOS);
    }

}
