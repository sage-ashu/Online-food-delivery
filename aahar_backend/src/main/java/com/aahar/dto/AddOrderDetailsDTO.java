package com.aahar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class AddOrderDetailsDTO {
    private Long dishId;
    private int quantity;
}

