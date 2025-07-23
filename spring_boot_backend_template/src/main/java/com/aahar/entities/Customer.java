package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer extends BaseEntity {
	private String firstName;
	private String lastName;
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
