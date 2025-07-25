package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.RestaurantOwnerDTO;
import com.aahar.services.RestaurantOwnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/owner")
@AllArgsConstructor
public class RestaurantOwnerController {
	public final RestaurantOwnerService restaurantOwnerService;
	
	//register the owner of the restaurant
	@PostMapping("/register")
	public ResponseEntity<?> addOwner(@RequestBody RestaurantOwnerDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.addOwner(dto));
	}
}
