package com.aahar.entities;

import java.time.LocalDateTime;

public class Restaurant extends BaseEntity{
		
	private String restaurantName;
	private String restauratDescription;
	private boolean isVeg;
	private double AvgCost;
	private double ratingSum;
	private double totalRating;
	private boolean isOnline;
	
	
	public Restaurant(Long id, LocalDateTime isCreatedAt, LocalDateTime isUpdatedAt, boolean isDeleted,
			String restaurantName, String restauratDescription, boolean isVeg, double avgCost, double ratingSum,
			double totalRating, boolean isOnline) {
		super(id, isCreatedAt, isUpdatedAt, isDeleted);
		this.restaurantName = restaurantName;
		this.restauratDescription = restauratDescription;
		this.isVeg = isVeg;
		AvgCost = avgCost;
		this.ratingSum = ratingSum;
		this.totalRating = totalRating;
		this.isOnline = isOnline;
	}
	
	

}
