package com.aahar.dto;

import jakarta.persistence.Column;

public class RestaurantInfoForCustomerDTO 
{
	private Long customerId;
	private String restaurantName;
    private String restauratDescription;
	private boolean isVeg;
	private double avgCost;
	private double ratingSum;
	private double totalRating;
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
