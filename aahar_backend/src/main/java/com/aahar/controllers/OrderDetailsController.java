package com.aahar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.OrderDetailsDTO;
import com.aahar.dto.RestaurantInfoDTO;
import com.aahar.services.OrderDetailsService;
//import com.aahar.services.orderDetailsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order_details")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class OrderDetailsController {
	private final OrderDetailsService orderDetailsService;
	//1.Add order details by order id and dish id
//	@PostMapping(("/add/{orderId}/{dishId}"))
//	public ResponseEntity<?> addOrderDetails(@PathVariable Long orderId, @PathVariable Long dishId, @RequestBody OrderDetailsDTO dto){
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED)
//					.body(orderDetailsService.addOrderDetails (orderId, dishId, dto));
//			
//			
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add order details: "+e.getMessage());
//		}
//		
//		
//		 
//	} 
	
	//2. get order details list by order id
}
