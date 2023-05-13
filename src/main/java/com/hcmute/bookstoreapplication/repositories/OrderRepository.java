package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
