package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DishUploadDTO {
	private Long restaurantId;
	private String dishName;
	private double dishPrice;
	private String description;
	private boolean isVeg;
	private int preperationTime;
	private int noOfServings;
	private boolean isAvailable;
}
