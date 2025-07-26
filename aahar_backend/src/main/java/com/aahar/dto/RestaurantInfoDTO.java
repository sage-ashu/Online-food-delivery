package com.aahar.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantInfoDTO {
    private Long restaurantId;
    private String restaurantName;
    private String restauratDescription;
    private boolean isVeg;
    private double avgCost;
    private boolean isOnline;

    // Optional ratings
    private double ratingSum;
    private double totalRating;

    // Address info
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String pinCode;
//    private String phone;

    // Owner basic info
    private Long ownerId;
    private String ownerName; // optional if needed
}

