package com.aahar.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantOwnerUpdateDTO {

	
	
	private String name;
	
	private String phoneNumber;
	
	private String email;
	
	private String password;
	
}
