package com.aahar.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.config.JwtUtil;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.PasswordUpdateDTO;
import com.aahar.dto.RestaurantOwnerLoginDTO;
import com.aahar.dto.RestaurantOwnerRegistrationDTO;
import com.aahar.dto.RestaurantOwnerUpdateDTO;
import com.aahar.services.RestaurantOwnerService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/owner")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantOwnerController {
	
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authManager;
    private final RestaurantOwnerService ownerService;
	
	//-1 Register owner
	   @PostMapping("/register")
	    public ResponseEntity<?> registerOwner(@RequestBody RestaurantOwnerRegistrationDTO dto) {
	        ApiResponse response = ownerService.registerOwner(dto);
	        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
	    }
	
	
	
	// 0 Login owner
	   @PostMapping("/login")
	    public ResponseEntity<?> loginOwner(@RequestBody RestaurantOwnerLoginDTO dto) {
		   Authentication auth = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
		   auth =authManager.authenticate(auth);
		   String token = jwtUtil.createToken(auth);
		   return ResponseEntity.ok(token);
		   }
	   
	
	//1.update owner details
	
	 @PutMapping("/update")
	    public ResponseEntity<?> updateOwnerDetails(@RequestBody RestaurantOwnerUpdateDTO dto, HttpServletRequest request) {
		 String token = jwtUtil.extractTokenFromRequest(request);
		 Long ownerId = jwtUtil.extractId(token);
	        ApiResponse response = ownerService.updateOwnerDetails(ownerId, dto);
	        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
	    }
	
	
	//2. update password
	 @PutMapping("/updatepassword")
	 public ResponseEntity<?> updateOwnerPassword(@RequestBody PasswordUpdateDTO dto,HttpServletRequest request) {
		 String token = jwtUtil.extractTokenFromRequest(request);
		 Long ownerId = jwtUtil.extractId(token);
	     ApiResponse response = ownerService.updateOwnerPassword(ownerId, dto);
	     return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
	 }

	//3. get all details
	 @GetMapping("/profile")
	 public ResponseEntity<?> getOwnerDetails(HttpServletRequest request){
		 String token = jwtUtil.extractTokenFromRequest(request);
		 Long ownerId = jwtUtil.extractId(token);
		 ApiResponse response = ownerService.getOwnerDetails(ownerId);
	     return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
	 }
	 
	
}
