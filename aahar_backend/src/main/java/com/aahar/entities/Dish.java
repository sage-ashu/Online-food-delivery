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
	@Column(length=40)	
	private String dishName;
	private double dishPrice;
	@Column(length=150)	
	private String description;
	private boolean isVeg;
	private int preperationTime;
	
	//Doubt not to be included 
	private int noOfServings;
	
	//Please have mercy on developer
	private int orderedTimes;
	
	//I am dying
	private int ratingSum;
	//We are humans too
	private boolean isAvailable;
	@ManyToOne
	@JoinColumn(name="restaurant",nullable = false)
	private Restaurant myRestaurant;
	public Dish(String dishName,
			double dishPrice, String description, boolean isVeg, int preperationTime, int noOfServings,
			 boolean isAvailable) 
	{
		super();
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.description = description;
		this.isVeg = isVeg;
		this.preperationTime = preperationTime;
		this.noOfServings = noOfServings;
		this.orderedTimes = 0;
		this.ratingSum = 0;
		this.isAvailable = isAvailable;
	}
	
	
}
