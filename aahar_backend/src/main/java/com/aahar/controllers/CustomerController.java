package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	public final CustomerService customerService;
	//add customer
	//get all orders of user
	//get saved address
	//Add Address
	@PostMapping("/{customerId}/address")
	public ResponseEntity<?> addAddress(@PathVariable Long customerId,@RequestBody AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(customerService.addAddress(customerId,dto));
	}
	//Update Address
	
	//Delete Address
	//update Password
	
	
}
