package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    // No `orders` or `addresses` unless explicitly needed
}
