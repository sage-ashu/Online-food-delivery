package com.aahar.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class RestaurantOwner extends BaseEntity{
	
	@Column(length=40)
	private String name;
	@Column(length=40)
	private String phoneNumber;
	@Column(length=40)
	private String email;
	@Column(length=40)
	private String password;
	public RestaurantOwner(String name, String phoneNumber, String email, String password) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	@OneToMany(mappedBy = "restaurantOwner" , cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Restaurant> restaurant = new ArrayList<>();
}
