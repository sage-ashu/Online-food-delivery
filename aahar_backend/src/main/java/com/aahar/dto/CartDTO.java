package com.aahar.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDTO {
    private Long customerId;
    private Long restaurantId;
    private List<CartItemDTO> items;
}
