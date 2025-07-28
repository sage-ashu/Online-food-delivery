package com.aahar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.CustomerAddress;

public interface CustomerAddressDao extends JpaRepository<CustomerAddress,Long> {

	List<CustomerAddress> findByCustomerId(Long customerId);

}
