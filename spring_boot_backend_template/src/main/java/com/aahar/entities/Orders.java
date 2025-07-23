package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders extends BaseEntity {

	
	private LocalDateTime orderDateTime;
	private double orderAmount;
	private double deliveryDistance;
	private double deliveryCharge;
	private double orderTotal;
	
	 // This is many to one relationship multiple orders can belong to one customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	
	public Orders(LocalDateTime orderDateTime, double orderAmount, double deliveryDistance, double deliveryCharge,
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

