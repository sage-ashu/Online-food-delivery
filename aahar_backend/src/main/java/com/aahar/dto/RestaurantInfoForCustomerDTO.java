package com.aahar.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantInfoForCustomerDTO 
{
	//private Long customerId;
	private Long Id;
	private String restaurantName;
    private String restauratDescription;
	private boolean isVeg;
	private double avgCost;
	private String phoneNo ;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String pinCode;
	private double rating;

}
