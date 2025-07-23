package com.aahar.entities;

import java.time.LocalDateTime;

public class OrderDetails extends BaseEntity {
	int quantity;
	int rating;
	String review;
	
	
	public OrderDetails(  int quantity,
			int rating, String review) {
		super();
		this.quantity = quantity;
		this.rating = rating;
		this.review = review;
	}
	
	
	
	
}
