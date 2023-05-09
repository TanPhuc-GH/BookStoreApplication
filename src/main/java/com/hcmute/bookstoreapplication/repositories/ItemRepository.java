package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
