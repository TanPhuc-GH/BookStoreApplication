package com.hcmute.bookstoreapplication.dtos;

import com.hcmute.bookstoreapplication.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Integer id;

    private String itemName;

    private Integer quantity;

    private Float price;
    private Integer product_id;
    public ItemDTO(Item item){
        this.setId(item.getId());
        this.setItemName(item.getItemName());
        this.setQuantity(item.getQuantity());
        this.setPrice(item.getPrice());
        this.setProduct_id(item.getProduct().getId());
    }
}
