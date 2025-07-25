package com.aahar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aahar.entities.RestaurantOwner;

public interface RestaurantOwnerDao  extends JpaRepository<RestaurantOwner, Long>{

}
