package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.services.UserService;

import lombok.AllArgsConstructor;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
	public final UserService userService;

	//get all orders of user
	//get saved address
	//Add Address
	@PostMapping("/{userId}/address")
	public ResponseEntity<?> addAddress(@PathVariable Long userId,@RequestBody AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.addAddress(userId,dto));
	}
	//Update Address
	//Delete Address
	//update Password
	
	
}
