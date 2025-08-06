package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantInfoForOwnerDTO {
    private String restaurantName;
    private String restaurantDescription;
    private boolean veg;       // ✅ renamed from isVeg → veg
    private double avgCost;
    private boolean online;    // ✅ renamed from isOnline → online
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

