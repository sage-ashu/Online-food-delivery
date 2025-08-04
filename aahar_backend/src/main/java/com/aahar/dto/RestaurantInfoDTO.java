package com.aahar.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantInfoDTO {
    private Long Id;
    private String restaurantName;
    private String restaurantDescription;
    private boolean isVeg;
    private double avgCost;
    private boolean isOnline;

   // Owner basic info
    private Long ownerId;
    private String ownerName; // optional if needed
}

