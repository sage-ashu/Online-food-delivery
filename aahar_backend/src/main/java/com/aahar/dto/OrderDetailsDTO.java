package com.aahar.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {
	private int quantity;
	private int rating;
	private String review;

}
