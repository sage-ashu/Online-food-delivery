package com.aahar.entities;

import java.time.LocalDateTime;

public class Address extends BaseEntity{
	public Address(Long id, LocalDateTime isCreatedAt, LocalDateTime isUpdatedAt, boolean isDeleted) {
		super(id, isCreatedAt, isUpdatedAt, isDeleted);
		// TODO Auto-generated constructor stub
	}
	private String phoneNo ;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String pinCode;
	private double latitude;
	private double longitude;
	private boolean isRestaurant;
	

}
