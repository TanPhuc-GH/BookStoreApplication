package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select it " +
            "from Item it " +
            "where it.cart.id = :cartId")
    List<Item> findByCartId(@Param("cartId") Integer cartId);
    @Query("select i " +
            "from Item i " +
            "where i.product.id = :productId and i.cart.id = :cartId")
    Item findByProductIdAndCartId(@Param("productId") Integer productId, @Param("cartId") Integer cartId);
}
