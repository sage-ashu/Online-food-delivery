package com.aahar.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Dish extends BaseEntity{
	@Column(length=10)	
	private String dishName;
	private double dishPrice;
	@Column(length=30)	
	private String description;
	private boolean isVeg;
	private int preperationTime;
	private int noOfServings;
	private int orderedTimes;
	private int ratingSum;
	private boolean isAvailable;
	@ManyToOne
	@JoinColumn(name="restaurant",nullable = false)
	private Restaurant myRestaurant;
	public Dish(String dishName,
			double dishPrice, String description, boolean isVeg, int preperationTime, int noOfServings,
			int orderedTimes, int ratingSum, boolean isAvailable) 
	{
		super();
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
