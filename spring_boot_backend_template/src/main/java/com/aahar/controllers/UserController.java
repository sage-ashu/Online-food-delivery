package com.aahar.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.services.UserService;

import lombok.AllArgsConstructor;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
	public final UserService userService;

	/*
	 * Add Food Item
      URL - http://host:port/food_items/{restaurantId}
      Method - POST
      Payload - food item dto - details
      error resp - ApiResp dto - SC 400 , mesg -adding food item failed
      success resp - ApiResp dto - SC 201 , success mesg
	 */
	
}
