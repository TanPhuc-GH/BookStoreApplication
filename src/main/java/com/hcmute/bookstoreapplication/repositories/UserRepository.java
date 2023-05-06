package com.hcmute.bookstoreapplication.repositories;

import com.hcmute.bookstoreapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}
