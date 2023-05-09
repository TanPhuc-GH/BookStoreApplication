package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
