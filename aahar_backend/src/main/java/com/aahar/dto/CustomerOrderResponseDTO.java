package com.aahar.dto;

import java.time.LocalDateTime;

import com.aahar.entities.OrderStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class CustomerOrderResponseDTO {
    private Long orderId;
    private LocalDateTime orderDateTime;
    private double orderTotal;
    private String restaurantName;
    private OrderStatus status;
}
