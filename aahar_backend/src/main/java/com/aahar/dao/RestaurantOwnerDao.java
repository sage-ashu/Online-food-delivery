package com.aahar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aahar.entities.RestaurantOwner;

public interface RestaurantOwnerDao extends JpaRepository<RestaurantOwner, Long> {
	boolean existsByEmail(String email);
	Optional<RestaurantOwner> findByEmail(String email);
//	Optional<RestaurantOwner> findById(Long id);
}
