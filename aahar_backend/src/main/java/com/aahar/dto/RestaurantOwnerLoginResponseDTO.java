package com.aahar.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOwnerLoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String role = "restaurant";
}
