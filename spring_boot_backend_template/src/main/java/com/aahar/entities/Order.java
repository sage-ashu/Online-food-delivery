package com.aahar.entities;

import java.time.LocalDateTime;

public class Order extends BaseEntity {

	private LocalDateTime orderDateTime;
	private double orderAmount;
	private double deliveryDistance;
	private double deliveryCharge;
	private double orderTotal;
}
