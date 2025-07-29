package com.aahar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantOwnerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
