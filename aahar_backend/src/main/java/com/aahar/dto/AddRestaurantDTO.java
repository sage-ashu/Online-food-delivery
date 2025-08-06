package com.aahar.dto;



import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRestaurantDTO {
    private Long id; // Optional: used for update scenario
    private Long ownerId;

    private String restaurantName;
    private String restaurantDescription;
    private boolean isVeg;
    private double avgCost;
    private boolean isOnline;
    private String phoneNo;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String pinCode;
    private double latitude;
    private double longitude;
}

