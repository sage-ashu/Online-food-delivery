package com.aahar.entities;

import java.time.LocalDateTime;

public class RestaurantOwner extends BaseEntity{
	
	
	String name;
	String phoneNumber;
	String email;
	String password;
	public RestaurantOwner(String name, String phoneNumber, String email, String password) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
}
