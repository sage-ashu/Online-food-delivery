package com.aahar.controllers;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.PasswordUpdateDTO;
import com.aahar.dto.RestaurantOwnerLoginDTO;
import com.aahar.dto.RestaurantOwnerRegistrationDTO;
//import com.aahar.dto.RestaurantOwnerLoginResponseDTO;
import com.aahar.dto.RestaurantOwnerUpdateDTO;
import com.aahar.services.RestaurantOwnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/owner")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantOwnerController {
	
	
	@Autowired
    private RestaurantOwnerService ownerService;
	
	//-1 Register owner
	   @PostMapping("/register")
	    public ResponseEntity<?> registerOwner(@RequestBody RestaurantOwnerRegistrationDTO dto) {
	        ApiResponse response = ownerService.registerOwner(dto);
	        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
	    }
	
	
	
	// 0 Login owner
	   @PostMapping("/login")
	    public ResponseEntity<?> loginOwner(@RequestBody RestaurantOwnerLoginDTO dto) {
		   System.out.println("DTO Email: " + dto.getEmail());
		   System.out.println("DTO Password: " + dto.getPassword());
	        ApiResponse response = ownerService.loginOwner(dto);
	        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
	    }
	   
	
	//1.update owner details
	
	 @PutMapping("/update/{ownerId}")
	    public ResponseEntity<?> updateOwnerDetails(@RequestBody RestaurantOwnerUpdateDTO dto,
	                                                @PathVariable Long ownerId) {
	        ApiResponse response = ownerService.updateOwnerDetails(ownerId, dto);
	        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
	    }
	
	
	//2. update password
	 @PutMapping("/updatepassword/{ownerId}")
	 public ResponseEntity<?> updateOwnerPassword(@RequestBody PasswordUpdateDTO dto,
	                                              @PathVariable Long ownerId) {
	     ApiResponse response = ownerService.updateOwnerPassword(ownerId, dto);
	     return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
	 }
	 
	 

	//3. get all details
	 // 
	 
	 @GetMapping("/{ownerId}")
	 public ResponseEntity<?> getOwnerDetails(@PathVariable Long ownerId){
		 
		 ApiResponse response = ownerService.getOwnerDetails(ownerId);
	     return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
	 }
	 
	
}
