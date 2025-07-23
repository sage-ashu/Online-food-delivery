package com.aahar.entities;

import java.time.LocalDateTime;

public class RestaurantOwner extends BaseEntity{
	
	
	String name;
	String phoneNumber;
	String email;
	String password;
	public RestaurantOwner(Long id, LocalDateTime isCreatedAt, LocalDateTime isUpdatedAt, boolean isDeleted,
			String name, String phoneNumber, String email, String password) {
		super(id, isCreatedAt, isUpdatedAt, isDeleted);
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
}
