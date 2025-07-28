package com.aahar.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerAddressDTO {
    private Long id;
    private String phoneNo;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String pinCode;
    private double latitude;
    private double longitude;

    private Long customerId;
}
