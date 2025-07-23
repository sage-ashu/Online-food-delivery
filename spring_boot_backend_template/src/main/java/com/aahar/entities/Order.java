package com.aahar.entities;

import java.time.LocalDateTime;

public class Order extends BaseEntity {

	
	private LocalDateTime orderDateTime;
	private double orderAmount;
	private double deliveryDistance;
	private double deliveryCharge;
	private double orderTotal;
	
	public Order(LocalDateTime orderDateTime, double orderAmount, double deliveryDistance, double deliveryCharge,
			double orderTotal) 
	{
		super();
		this.orderDateTime = orderDateTime;
		this.orderAmount = orderAmount;
		this.deliveryDistance = deliveryDistance;
		this.deliveryCharge = deliveryCharge;
		this.orderTotal = orderTotal;
	}
	
	
}

