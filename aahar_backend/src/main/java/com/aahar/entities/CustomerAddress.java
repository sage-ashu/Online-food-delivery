package com.aahar.entities;


import jakarta.persistence.Column;
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
public class CustomerAddress extends BaseEntity
{
	@Column(length=10)
	private String phoneNo ;
	@Column(length=20)
	private String address1;
	@Column(length=20)
	private String address2;
	@Column(length=20)
	private String address3;
	@Column(length=10)
	private String city;
	@Column(length=6)
	private String pinCode;
	private double latitude=0.0;
	private double longitude=0.0;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	public CustomerAddress( String phoneNo,String address1, String address2, String address3, String city, String pinCode, double latitude,
			double longitude) {
		super();
		this.phoneNo = phoneNo;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.pinCode = pinCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
//	
	
	

}
