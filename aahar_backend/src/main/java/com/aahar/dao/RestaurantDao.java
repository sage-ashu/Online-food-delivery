package com.aahar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Restaurant;



public interface RestaurantDao extends JpaRepository<Restaurant, Long>{
	Optional<Restaurant> findByRestaurantOwnerId(Long ownerId);
    List<Restaurant> findByCity(String city);
   
	
}
