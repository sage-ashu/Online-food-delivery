package com.aahar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class CustomerAddrs extends Address{
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public CustomerAddrs(String phoneNo, String address1, String address2, String address3, String city, String pinCode,
			double latitude, double longitude, boolean isRestaurant, Customer customer) {
		super(phoneNo, address1, address2, address3, city, pinCode, latitude, longitude, isRestaurant);
		this.customer = customer;
	}
	
}
