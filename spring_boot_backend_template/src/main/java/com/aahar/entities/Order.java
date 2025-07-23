package com.aahar.entities;

import java.time.LocalDateTime;

public class Order extends BaseEntity {

	public Order(Long id, LocalDateTime isCreatedAt, LocalDateTime isUpdatedAt, boolean isDeleted) {
		super(id, isCreatedAt, isUpdatedAt, isDeleted);
		// TODO Auto-generated constructor stub
	}
	private LocalDateTime orderDateTime;
	private double orderAmount;
	private double deliveryDistance;
	private double deliveryCharge;
	private double orderTotal;
}
