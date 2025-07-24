package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>{

}
