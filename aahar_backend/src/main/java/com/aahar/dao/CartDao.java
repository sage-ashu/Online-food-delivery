package com.aahar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Cart;
import com.aahar.entities.Customer;

public interface CartDao extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
}
