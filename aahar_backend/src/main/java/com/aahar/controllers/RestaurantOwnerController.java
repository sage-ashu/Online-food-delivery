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
	
	//register the owner of the restaurant
	@PostMapping("/register")
	public ResponseEntity<?> addOwner(@RequestBody RestaurantOwnerDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.addOwner(dto));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginOwner(@RequestBody RestaurantOwnerLoginRequestDTO requestDto) {
	    try {
	    	System.out.println(requestDto);
	        RestaurantOwnerLoginResponseDTO responseDTO = restaurantOwnerService.loginOwner(requestDto);
	        System.out.println(responseDTO);
	        return ResponseEntity.ok(responseDTO);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage()));
	    }
	}
	
	//1.update owner details
	//2. update password
	//3. get all details
	
}
