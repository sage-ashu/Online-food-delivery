package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.AddressDTO;
import com.aahar.dto.CustomerDTO;
import com.aahar.services.CustomerService;

import lombok.AllArgsConstructor;

@RequestMapping("/customer")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
	public final CustomerService customerService;
	
	//add customer
	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(dto));
	}
	
	//get all orders of user->move to orders controller
	@GetMapping("/{customerId}/orders")
	public ResponseEntity<?> allOrders(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.allOrders(id));
	}
	//get saved address
	
	
	//Add Address->move to address controller
	@PostMapping("/{customerId}/address")
	public ResponseEntity<?> addAddress(@PathVariable Long customerId,@RequestBody AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(customerService.addAddress(customerId,dto));
	}
	//Update Address
	
	//Delete Address
	//1.update Password
	//2. update details
	//3. get all details
	
	
	
}
