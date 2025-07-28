package com.aahar.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerOrderResponseDTO {
	Long orderId;
	private LocalDateTime orderDateTime;
//	private double orderAmount;
//	private double deliveryDistance;
//	private double deliveryCharge;
	private double orderTotal;
	String restaurantName;
//	Long restaurantId;
//	Long customerId;
}
