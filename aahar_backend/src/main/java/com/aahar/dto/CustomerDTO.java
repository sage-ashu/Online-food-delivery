package com.aahar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	private String firstName;
	
	private String lastName;
	
	private String email;
	/*this DTO is used in both the cases 
		1. while adding the customer
		2.while displaying the customer profile
	this jsonproperty annotation will hide the password while display
	*
	*/
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
