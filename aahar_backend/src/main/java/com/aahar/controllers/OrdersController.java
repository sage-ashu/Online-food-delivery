package com.aahar.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.services.OrdersService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
//	public final OrdersService ordersService;
	
	//1. add Orders by customer id and restaurant id
	//2. get order by customer id
	//3. get order by restaurant id
	//4. update order status by order id
	
}
