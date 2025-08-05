package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
}
