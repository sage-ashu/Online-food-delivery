package com.aahar.services;

import java.util.List;

import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;

public interface OrdersService {

	List<CustomerOrderResponseDTO> getCustomerOrders(Long customerId);

	List<RestaurantOrderResponseDTO> getRestaurantOrders(Long restaurantId);

}
