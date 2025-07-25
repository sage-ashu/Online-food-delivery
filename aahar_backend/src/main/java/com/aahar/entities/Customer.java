package com.aahar.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
public class Customer extends BaseEntity
{
	@Column(length=50, name="first_name")
	private String firstName;
	@Column(length=50, name="last_name")
	private String lastName;
	@Column(length=100, unique=true)
	private String email;
	private String password;
	
	/*
	 * one customer can place multiple orders,
	therefore this is one to many relationship
	*/
	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Orders> orders=new ArrayList<>();
	
	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	private List< CustomerAddress> addresses=new ArrayList<>();
	
	
	//helper class to add address
	public void addAddress( CustomerAddress addressEntity) {
		this.addresses.add(addressEntity);
		addressEntity.setCustomer(this);
	}
	
	public void addOrder( Orders orderEntity) {
		this.orders.add(orderEntity);
		orderEntity.setCustomer(this);
	}
	public void removeAddress(CustomerAddress addressEntity) {
		this.addresses.remove(addressEntity);
		addressEntity.setCustomer(null);
	}
	
	public void removeOrder(Orders orderEntity) {
		this.orders.remove(orderEntity);
		orderEntity.setCustomer(null);
	}
	

}
