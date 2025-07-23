package com.aahar.entities;

import java.time.LocalDateTime;

public class Address extends BaseEntity
{
	
	private String phoneNo ;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String pinCode;
	private double latitude;
	private double longitude;
	private boolean isRestaurant;
	
	public Address(String phoneNo,
			String address1, String address2, String address3, String city, String pinCode, double latitude,
			double longitude, boolean isRestaurant) 
	{
		super();
		this.phoneNo = phoneNo;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.pinCode = pinCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isRestaurant = isRestaurant;
	}
	
	

}
