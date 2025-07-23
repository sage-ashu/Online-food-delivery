package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;

public class Customer extends BaseEntity
{
	@Column(length=50, name="first_name")
	private String firstName;
	@Column(length=50, name="last_name")
	private String lastName;
	@Column(length=100, unique=true)
	private String email;
	private String password;
	private List<Order> order=new ArrayList<>();
	
	public Customer(String firstName,
			String lastName, String email, String password, List<Order> order) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.order = order;
	}
	
	

}
