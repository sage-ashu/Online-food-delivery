package com.aahar.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends BaseEntity {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Order> order=new ArrayList<>();

}
