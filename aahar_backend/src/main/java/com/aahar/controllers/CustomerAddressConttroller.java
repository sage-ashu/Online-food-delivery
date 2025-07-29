package com.aahar.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aahar.dto.CustomerAddressDTO;
import com.aahar.dto.CustomerAddressEditDTO;
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
	 @PutMapping("/{id}")
	    public ResponseEntity<CustomerAddressEditDTO> editCustomerAddress(@PathVariable Long id, @RequestBody CustomerAddressEditDTO dto) {
	        return ResponseEntity.ok(customerAddressService.editCustomerAddress(id, dto));
	    }
	//3. Delete Address
	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteCustomerAddress(@PathVariable Long id) {
	        customerAddressService.deleteCustomerAddress(id);
	        return ResponseEntity.ok("Customer address deleted successfully");
	    }
	//4.get list of addresses
	 @GetMapping
	    public ResponseEntity<List<CustomerAddressDTO>> getAllCustomerAddresses() {
	        List<CustomerAddressDTO> addresses = customerAddressService.getAllCustomerAddresses();
	        if (addresses.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	        return ResponseEntity.ok(addresses);
	    }
	  @GetMapping("/customer/{customerId}")
	    public ResponseEntity<List<CustomerAddressDTO>> getAddressesByCustomerId(@PathVariable Long customerId) {
	        List<CustomerAddressDTO> addresses = customerAddressService.getAddressesByCustomerId(customerId);
	        return ResponseEntity.ok(addresses);
	    }
	  
}
