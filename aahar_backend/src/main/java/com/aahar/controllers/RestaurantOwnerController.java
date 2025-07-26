package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantOwnerDTO;
import com.aahar.dto.RestaurantOwnerLoginRequestDTO;
import com.aahar.dto.RestaurantOwnerLoginResponseDTO;
import com.aahar.services.RestaurantOwnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/owner")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantOwnerController {
	public final RestaurantOwnerService restaurantOwnerService;
	
	
	//-1 Register owner
	// 0 Login owner
	
	//1.update owner details
	//2. update password
	//3. get all details
	
}
