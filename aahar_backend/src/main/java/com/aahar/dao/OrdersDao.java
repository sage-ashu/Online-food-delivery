package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Orders;

public interface OrdersDao extends JpaRepository<Orders, Long> {

}
