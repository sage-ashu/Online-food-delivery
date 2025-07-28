package com.aahar.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.AddOrderDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;
import com.aahar.services.OrdersService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
	public final OrdersService ordersService;

	//1. add Orders by customer id and restaurant id
//	@PostMapping("")
	public ResponseEntity<?> addOrder(@RequestBody AddOrderDTO orderDTO){
		ordersService.addOrder(orderDTO);
		return ResponseEntity.ok("order placed");
	}
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
