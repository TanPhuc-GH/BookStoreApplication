package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Integer id;

    private String itemName;

    private Integer quantity;

    private Float price;

    private Integer productId;


    public ItemDTO(Item item){
        this.setId(item.getId());
        this.setItemName(item.getItemName());
        this.setQuantity(item.getQuantity());
        this.setPrice(item.getPrice());
        this.setProductId(item.getProduct().getId());
    }
}
