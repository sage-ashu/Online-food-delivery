package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RestaurantAddressDTO {
    private String phoneNo;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String pinCode;
    private double latitude;
    private double longitude;

    private Long restaurantId;
    private Long ownerId;
}

