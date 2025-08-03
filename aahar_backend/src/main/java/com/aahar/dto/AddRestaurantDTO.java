package com.aahar.dto;



import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRestaurantDTO {
    private Long ownerId; // To link the restaurant with the owner

	private String restaurantName;
	private String restauratDescription;
	private boolean isVeg;
	private double avgCost;
	private boolean isOnline;
	private String phoneNo ;
    private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
    private String pinCode;
	
	private double latitude;
	private double longitude;
}
