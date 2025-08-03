package com.aahar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Restaurant;



public interface RestaurantDao extends JpaRepository<Restaurant, Long>{
	List<Restaurant> findByRestaurantOwnerId(Long ownerId);
    List<Restaurant> findByCity(String city);
	
}
