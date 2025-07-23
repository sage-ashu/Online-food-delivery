package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer extends BaseEntity {
	public Customer(Long id, LocalDateTime isCreatedAt, LocalDateTime isUpdatedAt, boolean isDeleted) {
		super(id, isCreatedAt, isUpdatedAt, isDeleted);
		// TODO Auto-generated constructor stub
	}
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Order> order=new ArrayList<>();

}
