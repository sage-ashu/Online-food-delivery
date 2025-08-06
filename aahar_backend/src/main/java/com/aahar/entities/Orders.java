package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Orders extends BaseEntity {

	
	private LocalDateTime orderDateTime;
	private double orderAmount;
	private double deliveryDistance;
	private double deliveryCharge;
	private double orderTotal;

	private String review;
	
	private Double rating;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	 // This is many to one relationship multiple orders can belong to one customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	@OneToMany(mappedBy = "orders", cascade=CascadeType.ALL,orphanRemoval = true)
	private List<OrderDetails> orderdetails=new ArrayList<>();
	
	public Orders(double orderAmount, double deliveryDistance, double deliveryCharge,
			double orderTotal) 
	{
		super();
		this.orderDateTime = LocalDateTime.now();
		this.orderAmount = orderAmount;
		this.deliveryDistance = deliveryDistance;
		this.deliveryCharge = deliveryCharge;
		this.orderTotal = orderTotal;
		this.rating = 0.0;
	}
	
	public void addOrderDetail(OrderDetails orderDetail) {
		this.orderdetails.add(orderDetail);
		orderDetail.setOrders(this);
	}
	
	
	
	
}

