package com.aahar.dto;

import com.aahar.entities.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusDTO {
    private OrderStatus status;
}

