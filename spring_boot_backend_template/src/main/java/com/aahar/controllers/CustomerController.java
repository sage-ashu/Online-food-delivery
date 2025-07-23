package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.AddressDTO;
import com.aahar.services.CustomerService;

import lombok.AllArgsConstructor;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class CustomerController {
	public final CustomerService customerService;

	//get all orders of user
	//get saved address
	//Add Address
	@PostMapping("/address")
	public ResponseEntity<?> addAddress(@RequestBody AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(customerService.addAddress(dto));
	}
	//Update Address
	//Delete Address
	//update Password
	
	
}
