package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.CustomerAddress;

public interface CustomerAddressDao extends JpaRepository<CustomerAddress,Long> {

}
