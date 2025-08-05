package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;
//import com.aahar.dto.CustomerDTO;
import com.aahar.dto.CustomerLoginDTO;
import com.aahar.dto.CustomerRegisterDTO;
import com.aahar.dto.UpdatePasswordDTO;
import com.aahar.dto.updateCustomerDTO;
import com.aahar.services.CustomerService;

import lombok.AllArgsConstructor;

@RequestMapping("/customer")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

	public final CustomerService customerService;

//	// add customer
//	@PostMapping("/add")
//	public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO dto) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(dto));
//	}

	

	
//	@PostMapping("/login")
//	public ResponseEntity<ApiResponse> loginCustomer(@RequestBody CustomerLoginDTO dto){
//		return ResponseEntity.status(HttpStatus.FOUND).body(customerService.loginCustomer(dto));
//		
//	}
	
	
//	//get all orders of user->move to orders controller
//	@GetMapping("/{customerId}/orders")
//	public ResponseEntity<?> allOrders(@PathVariable Long customerId){
//		return ResponseEntity.status(HttpStatus.OK).body(customerService.allOrders(customerId));
//	}
//	
//	
//	
//	//Add Address->move to address controller
//	@PostMapping("/{customerId}/address")
//	public ResponseEntity<?> addAddress(@PathVariable Long customerId,@RequestBody AddressDTO dto){
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(customerService.addAddress(customerId,dto));
//	}

	// view customer profile
	@GetMapping("/{customerId}/profile")
	public ResponseEntity<?> customerProfile(@PathVariable Long customerId) {
		return ResponseEntity.ok(customerService.customerProfile(customerId));
	}

	// delete customer
	@DeleteMapping("/{customerId}/delete")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
		return ResponseEntity.ok(customerService.deleteCustomer(customerId));
	}

	// change password
	@PutMapping("/{customerId}/updatePassword")
	public ResponseEntity<?> updatePassword(@PathVariable Long customerId, @RequestBody UpdatePasswordDTO dto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updatePassword(customerId, dto));
	}

	// update profile
	@PutMapping("/{customerId}/updateProfile")
	public ResponseEntity<?> updateProfile(@PathVariable Long customerId, @RequestBody updateCustomerDTO dto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updateProfile(customerId, dto));

	}
	
	
	
	
	//Register user
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerCustomer(@RequestBody CustomerRegisterDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(dto));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginCustomer(@RequestBody CustomerLoginDTO dto){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerService.loginCustomer(dto));
	}
}
