package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
	private int noOfServings;
	private int orderedTimes;
	private int ratingSum;
	private boolean isAvailable;
	private String imagePath;
	@OneToMany(mappedBy = "dish", cascade=CascadeType.ALL,orphanRemoval = true)
	private List<OrderDetails> orderdetails=new ArrayList<>();
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
	
	public void addOrderDetail(OrderDetails detail) {
		this.orderdetails.add(detail);
		detail.setDish(this);
	}
	public double getAverageRating() {
	    return orderedTimes == 0 ? 0.0 : (double) ratingSum / orderedTimes;
	}

	
	
}
