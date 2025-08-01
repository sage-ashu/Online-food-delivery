package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRatingDTO {
	private Long orderId;
	private double ratings;
	private String review;
}
