package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.CustomerAddressDTO;
import com.aahar.services.CustomerAddressService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Customer-address")
public class CustomerAddressConttroller {
	private final CustomerAddressService customerAddressService;
	
	//1. Add Address
	@PostMapping
    public ResponseEntity<?> addCustomerAddress(@RequestBody CustomerAddressDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerAddressService.addCustomerAddress(dto));
    }
	//2.Edit address
	//3. Delete Address
	//4.get list of addresses
}
