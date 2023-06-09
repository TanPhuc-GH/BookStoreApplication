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

    private String thumbnailPath;

    private Integer productId;

    private Integer cartId;


    public ItemDTO(Item item){
        this.setId(item.getId());
        this.setItemName(item.getItemName());
        this.setQuantity(item.getQuantity());
        this.setPrice(item.getPrice());
        this.setThumbnailPath(item.getThumbnail());
        this.setProductId(item.getProduct().getId());
        this.setCartId(item.getCart().getId());
    }
}
