package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddOrderDetailsDTO {
	Long dishId;
	int quantity;
}
