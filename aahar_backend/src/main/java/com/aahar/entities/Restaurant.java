package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = {"restaurantOwner","dish","orders"})
public class Restaurant extends BaseEntity{
	@Column(length=50)	
	private String restaurantName;
	@Column(length=300)
	private String restauratDescription;
	private boolean isVeg;
	private double avgCost;
	private double ratingSum;
	private double totalRating;
	private boolean isOnline;
	@Column(length=10)
	private String phoneNo ;
	@Column(length=100)
	private String address1;
	@Column(length=100)
	private String address2;
	@Column(length=100)
	private String address3;
	@Column(length=50)
	private String city;
	@Column(length=50)
	private String state;
	@Column(length=6)
	private String pinCode;
	
	private double latitude;
	private double longitude;
	@OneToMany(mappedBy = "myRestaurant",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dish> dish=new ArrayList<>();
	@OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orders> orders=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="restaurantOwner_id")
	private RestaurantOwner restaurantOwner;
	
	public void addDish(Dish dish) {
		this.dish.add(dish);
		dish.setMyRestaurant(this);
	}
	
	public void removeDish(Dish dish) {
		this.dish.remove(dish);
		dish.setMyRestaurant(null);
	}
	
	public double getRating() {
		return this.getRatingSum()/this.getTotalRating();
	}

	public Restaurant(String restaurantName, String restauratDescription, boolean isVeg, double avgCost,
			 boolean isOnline, String phoneNo, String address1, String address2, String address3,
			String city, String pinCode, double latitude, double longitude) {
		super();
		this.restaurantName = restaurantName;
		this.restauratDescription = restauratDescription;
		this.isVeg = isVeg;
		this.avgCost = avgCost;
		this.ratingSum = 0;
		this.totalRating =0;
		this.isOnline = isOnline;
		this.phoneNo = phoneNo;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.pinCode = pinCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	
	
	}
	
	
