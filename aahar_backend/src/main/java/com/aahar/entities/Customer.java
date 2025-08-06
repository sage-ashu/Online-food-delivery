package com.aahar.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="customers")
@ToString(callSuper = true,exclude={"orders","addresses","cart"})
public class Customer extends BaseEntity
{
	
	@Column(length=50, name="first_name")
	private String firstName;
	@Column(length=50, name="last_name")
	private String lastName;
	@Column(length=100, unique=true)
	private String email;
	@Column(length=250, nullable = false)
	private String password;
	
	
	
	/*
	 * one customer can place multiple orders,
	therefore this is one to many relationship
	*/
	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Orders> orders=new ArrayList<>();
	


	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,orphanRemoval = true)
	private List< CustomerAddress> addresses=new ArrayList<>();
	
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;

	public void setCart(Cart cart) {
	    this.cart = cart;
	    if (cart != null) {
	        cart.setCustomer(this);
	    }
	}


	     //helper class to add address
	
		public void addAddress( CustomerAddress address) 
		{
			this.addresses.add(address);
			address.setCustomer(this);
		}
		
		//helper class to add orders
		public void addOrders( Orders order) 
		{
			this.orders.add(order);
			order.setCustomer(this);
		}
		
		
		//helper class to remove address
		public void removeAddress(CustomerAddress address)
		{
			this.addresses.remove(address);
			address.setCustomer(null);
		}

		

		public Customer(String firstName, String lastName, String email, String password) {

			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}

		

		
	
	

}


