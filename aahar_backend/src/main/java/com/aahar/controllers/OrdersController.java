package com.aahar.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.OrderDTO;
import com.aahar.services.OrdersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

	//public final OrdersService ordersService;

//	@PostMapping("")
	public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO){
		return null;
	}
	//1. add Orders by customer id and restaurant id
	//2. get order by customer id
	//3. get order by restaurant id
	//4. update order status by order id
	
}
