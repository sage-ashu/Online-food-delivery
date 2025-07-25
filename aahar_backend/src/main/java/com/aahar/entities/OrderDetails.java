package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

public class OrderDetails extends BaseEntity {
	int quantity;
	int rating;
	@Column(length=200)
	String review;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders orders;
	@ManyToOne
	@JoinColumn(name="orderdetails")
	private Dish dish;
	
	
	
	public OrderDetails(int quantity)
	{
		super();
		this.quantity = quantity;
		this.rating = 0;
		this.review = null;
	}
	
	
	
	
}
