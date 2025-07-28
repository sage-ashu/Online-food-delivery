package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.Dish;

public interface DishDao extends JpaRepository<Dish, Long> {

}
