package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString(callSuper = true, exclude = "orderDetails")
public class Dish extends BaseEntity {
	@Column(length = 40)
	private String dishName;
	private double dishPrice;
	@Column(length = 150)
	private String description;
	private boolean isVeg;
	private int preperationTime;
	private int noOfServings;
	private double totalRating;
	private int ratingSum;
	private int noOfRatings;
	private boolean isAvailable;
	private String imagePath;
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<OrderDetails> orderDetails = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant myRestaurant;

	public Dish(String dishName, double dishPrice, String description, boolean isVeg, int preperationTime,
			int noOfServings, boolean isAvailable) {
		super();
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.description = description;
		this.isVeg = isVeg;
		this.preperationTime = preperationTime;
		this.noOfServings = noOfServings;
		this.totalRating = 0;
		this.noOfRatings = 0;
		this.ratingSum = 0;
		this.isAvailable = isAvailable;
		this.myRestaurant = null;
	}

	public void addOrderDetail(OrderDetails detail) {
		this.orderDetails.add(detail);
		detail.setDish(this);
	}

	public double getRating() {
		return this.noOfRatings == 0 ? 0.0 : (double) this.ratingSum / this.noOfRatings;
	}

}
