package com.aahar.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantOrderResponseDTO {
	Long orderId;
	private LocalDateTime orderDateTime;
//	private double orderAmount;
//	private double deliveryDistance;
//	private double deliveryCharge;
	private double orderTotal;
	
}
