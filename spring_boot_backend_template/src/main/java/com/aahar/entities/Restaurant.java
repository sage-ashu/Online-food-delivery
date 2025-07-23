package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Restaurant extends BaseEntity{
	@Column(length=50)	
	private String restaurantName;
	@Column(length=300)
	private String restauratDescription;
	private boolean isVeg;
	private double AvgCost;
	private double ratingSum;
	private double totalRating;
	private boolean isOnline;
	@OneToMany(mappedBy = "myRestaurant",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dish> dish=new ArrayList<>();
	
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
	
	
