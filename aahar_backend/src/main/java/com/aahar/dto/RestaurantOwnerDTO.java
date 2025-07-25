package com.aahar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantOwnerDTO {

	private String name;
	private String phoneNumber;
	private String email;
	private String password;
}
