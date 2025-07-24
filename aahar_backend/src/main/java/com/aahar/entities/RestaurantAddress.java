package com.aahar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
public class RestaurantAddress extends Address{
	@OneToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;

	public RestaurantAddress(String phoneNo, String address1, String address2, String address3, String city,
			String pinCode, double latitude, double longitude, boolean isRestaurant, Restaurant restaurant) {
		super(phoneNo, address1, address2, address3, city, pinCode, latitude, longitude, isRestaurant);
		this.restaurant = restaurant;
	}
	
	

}
