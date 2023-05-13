package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
