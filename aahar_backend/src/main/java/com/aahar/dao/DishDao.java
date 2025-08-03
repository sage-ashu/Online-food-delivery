package com.aahar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Dish;

public interface DishDao extends JpaRepository<Dish, Long>{
	List<Dish> findByMyRestaurantId(Long restaurantId);

}
