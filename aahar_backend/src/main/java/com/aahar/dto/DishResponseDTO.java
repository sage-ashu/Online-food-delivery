package com.aahar.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DishResponseDTO {

	private Long id;
	private String dishName;
	private double dishPrice;
	private String description;
	private boolean isVeg;
	private double rating;
	private int preperationTime;
	private int noOfServings;
	private boolean isAvailable;
	private String imagePath;
//	private String restuarantName;
}

