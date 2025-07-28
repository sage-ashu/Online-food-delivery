package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.RestaurantAddress;

public interface RestaurantAddressDao extends JpaRepository<RestaurantAddress,Long>{
	

}
