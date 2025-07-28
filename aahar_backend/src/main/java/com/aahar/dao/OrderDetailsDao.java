package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.OrderDetails;

public interface OrderDetailsDao extends JpaRepository<OrderDetails, Long>{

}
