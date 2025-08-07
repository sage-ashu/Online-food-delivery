package com.aahar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private CustomerResponseDTO user;
    private String token;

    public LoginResponseDTO(CustomerResponseDTO user, String token) {
        this.user = user;
        this.token = token;
    }
}

