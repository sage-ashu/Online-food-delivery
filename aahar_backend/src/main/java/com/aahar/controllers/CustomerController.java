package com.aahar.controllers;


import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.config.JwtUtil;
import com.aahar.dao.CustomerDao;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.CustomerLoginDTO;
import com.aahar.dto.CustomerRegisterDTO;
import com.aahar.dto.CustomerResponseDTO;
import com.aahar.dto.LoginResponseDTO;
import com.aahar.dto.UpdatePasswordDTO;
import com.aahar.dto.updateCustomerDTO;
import com.aahar.entities.Customer;
import com.aahar.services.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RequestMapping("/customer")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

	private final CustomerService customerService;
	private final AuthenticationManager authManager;
	private final CustomerDao customerDao;
	private final ModelMapper map;
	private final JwtUtil jwtUtil;

	// view customer profile
	@GetMapping("/profile")
	public ResponseEntity<?> customerProfile(HttpServletRequest request) {
		String token = jwtUtil.extractTokenFromRequest(request);
		Long customerId = (Long) jwtUtil.extractId(token);
		return ResponseEntity.ok(customerService.customerProfile(customerId));
	}

	// delete customer
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCustomer(HttpServletRequest request) {
		String token = jwtUtil.extractTokenFromRequest(request);
		Long customerId = (Long) jwtUtil.extractId(token);
		return ResponseEntity.ok(customerService.deleteCustomer(customerId));
	}

	// change password
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(HttpServletRequest request, @RequestBody UpdatePasswordDTO dto) {
		String token = jwtUtil.extractTokenFromRequest(request);
		Long customerId = (Long) jwtUtil.extractId(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updatePassword(customerId, dto));
	}

	// update profile
	@PutMapping("/updateProfile")
	public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestBody updateCustomerDTO dto) {
		String token = jwtUtil.extractTokenFromRequest(request);
		Long customerId = (Long) jwtUtil.extractId(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updateProfile(customerId, dto));

	}
	
	//login
//	@PostMapping("/login")
//	public ResponseEntity<?> loginCustomer(@RequestBody CustomerLoginDTO dto){
//		Authentication auth = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
//		auth =authManager.authenticate(auth);
//		String token = jwtUtil.createToken(auth);
//		return ResponseEntity.ok(token);
//	}
//	
	@PostMapping("/login")
	public ResponseEntity<?> loginCustomer(@RequestBody CustomerLoginDTO dto) {
	    Authentication auth = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
	    auth = authManager.authenticate(auth);
	    String token = jwtUtil.createToken(auth);

	    Customer found = customerDao.findByEmail(dto.getEmail());
	    if (found == null) {
	        return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
	    }

	    CustomerResponseDTO customerDTO = map.map(found, CustomerResponseDTO.class);
	    LoginResponseDTO loginResponse = new LoginResponseDTO(customerDTO, token);

	    ApiResponse response = new ApiResponse(true, "Customer logged in successfully", loginResponse);
	    return ResponseEntity.ok(response);
	}


	
	//Register user
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerCustomer(@RequestBody CustomerRegisterDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(dto));
	}
	
}
