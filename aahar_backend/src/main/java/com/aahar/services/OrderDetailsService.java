package com.aahar.services;

import java.util.List;

import com.aahar.dto.AddOrderDTO;
import com.aahar.dto.AddOrderDetailsDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.OrderDetailsDTO;

public interface OrderDetailsService {

	void addOrderDetails(Long orderId,List<AddOrderDetailsDTO> orderDetails);

}
