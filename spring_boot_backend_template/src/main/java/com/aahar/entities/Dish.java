package com.aahar.entities;

import java.time.LocalDateTime;

public class Dish extends BaseEntity{
	String dishName;
	double dishPrice;
	String description;
	boolean isVeg;
	int preperationTime;
	int noOfServings;
	int orderedTimes;
	int ratingSum;
	boolean isAvailable;
	
	
	public Dish(Long id, LocalDateTime isCreatedAt, LocalDateTime isUpdatedAt, boolean isDeleted, String dishName,
			double dishPrice, String description, boolean isVeg, int preperationTime, int noOfServings,
			int orderedTimes, int ratingSum, boolean isAvailable) {
		super(id, isCreatedAt, isUpdatedAt, isDeleted);
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.description = description;
		this.isVeg = isVeg;
		this.preperationTime = preperationTime;
		this.noOfServings = noOfServings;
		this.orderedTimes = orderedTimes;
		this.ratingSum = ratingSum;
		this.isAvailable = isAvailable;
	}
	
	
}
