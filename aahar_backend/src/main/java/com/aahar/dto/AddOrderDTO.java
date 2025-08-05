package com.aahar.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class AddOrderDTO {
    private Long customerAddressId;
    private Long reaturantId;
    private List<AddOrderDetailsDTO> details;
}

