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
	
<<<<<<< HEAD
=======
	
>>>>>>> 8aa1d9e9e2f4a7ad4e52568220a0acab8d85fd19
	public Restaurant(String restaurantName, String restauratDescription, boolean isVeg, double avgCost, double ratingSum,
			double totalRating, boolean isOnline) {
		super();
		this.restaurantName = restaurantName;
		this.restauratDescription = restauratDescription;
		this.isVeg = isVeg;
		AvgCost = avgCost;
		this.ratingSum = ratingSum;
		this.totalRating = totalRating;
		this.isOnline = isOnline;
	}
	
	
	
	
	}
	
	
