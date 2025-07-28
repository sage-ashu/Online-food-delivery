package com.aahar.services;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.OrderDetailsDTO;

public interface OrderDetailsService {

	ApiResponse addOrderDetails(Long orderId, Long dishId, OrderDetailsDTO dto);

}
