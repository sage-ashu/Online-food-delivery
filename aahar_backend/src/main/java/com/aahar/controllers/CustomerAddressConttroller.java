package com.aahar.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.config.JwtUtil;
import com.aahar.dto.CustomerAddressDTO;
import com.aahar.dto.CustomerAddressEditDTO;
import com.aahar.services.CustomerAddressService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Customer-address")
@CrossOrigin(origins = "*")
public class CustomerAddressConttroller {
	private final CustomerAddressService customerAddressService;
	private final JwtUtil jwtUtil;
	//1. Add Address
	@PostMapping("/add")
    public ResponseEntity<?> addCustomerAddress(HttpServletRequest request,@RequestBody CustomerAddressDTO dto) {
		String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);  
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerAddressService.addCustomerAddress(customerId, dto));
    }
	//2.Edit address
	 @PutMapping("/update-address")
	    public ResponseEntity<CustomerAddressEditDTO> editCustomerAddress(HttpServletRequest request, @RequestBody CustomerAddressEditDTO dto) {
		 String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);   
		 return ResponseEntity.ok(customerAddressService.editCustomerAddress(customerId, dto));
	    }
	//3. Delete Address
	 @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteCustomerAddress(HttpServletRequest request) {
		 String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);   
	        customerAddressService.deleteCustomerAddress(customerId);
	        return ResponseEntity.ok("Customer address deleted successfully");
	    }
	//4.get list of addresses
	 @GetMapping("/all")
	    public ResponseEntity<List<CustomerAddressDTO>> getAllCustomerAddresses() {
	        List<CustomerAddressDTO> addresses = customerAddressService.getAllCustomerAddresses();
	        if (addresses.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	        return ResponseEntity.ok(addresses);
	    }
	  @GetMapping("/customer")
	    public ResponseEntity<List<CustomerAddressDTO>> getAddressesByCustomerId(HttpServletRequest request) {
		  String token = jwtUtil.extractTokenFromRequest(request);
			 Long customerId = jwtUtil.extractId(token);
	        List<CustomerAddressDTO> addresses = customerAddressService.getAddressesByCustomerId(customerId);
	        return ResponseEntity.ok(addresses);
	    }
	  
}
