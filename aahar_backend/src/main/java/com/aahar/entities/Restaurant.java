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
@ToString(callSuper = true)
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
	@OneToMany(mappedBy = "myRestaurant",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dish> dish=new ArrayList<>();
	
	@OneToOne( cascade=CascadeType.ALL)
	@JoinColumn(name="ResaturantAddress_id")
	private RestaurantAddress restaurantAddress;
	
	@ManyToOne
	@JoinColumn(name="restaurantOwner_id")
	private RestaurantOwner restaurantOwner;
	
	public Restaurant(String restaurantName, String restauratDescription, boolean isVeg, double avgCost, boolean isOnline) {
		super();
		this.restaurantName = restaurantName;
		this.restauratDescription = restauratDescription;
		this.isVeg = isVeg;
		this.avgCost = avgCost;
		this.ratingSum = 0;
		this.totalRating =0;
		this.isOnline = isOnline;
	}
	
	public void addDish(Dish dish) {
		this.dish.add(dish);
		dish.setMyRestaurant(this);
	}
	
	public void removeDish(Dish dish) {
		this.dish.remove(dish);
		dish.setMyRestaurant(null);
	}
	
	
	
	
	}
	
	
