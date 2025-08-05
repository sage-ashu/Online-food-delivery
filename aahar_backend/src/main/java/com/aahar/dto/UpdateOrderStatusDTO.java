package com.aahar.dto;

import com.aahar.entities.OrderStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UpdateOrderStatusDTO {
    private OrderStatus status;
}


