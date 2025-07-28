package com.aahar.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.OrderDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;
import com.aahar.services.OrdersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

	public final OrdersService ordersService;

//	@PostMapping("")
	public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO){
		return null;
	}
	//1. add Orders by customer id and restaurant id
	//2. get order by customer id
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getOrderByCustomer(Long customerId){
		List<CustomerOrderResponseDTO> orders = ordersService.getCustomerOrders(customerId);
		return ResponseEntity.ok(orders);
		
	}
	
	//3. get order by restaurant id
	@GetMapping("/{restaurantId}")
	public ResponseEntity<?> getOrderByRestaurant(Long restaurantId){
		List<RestaurantOrderResponseDTO> orders = ordersService.getRestaurantOrders(restaurantId);
		return ResponseEntity.ok(orders);
		
	}
	
	
	//4. update order status by order id
	
}
