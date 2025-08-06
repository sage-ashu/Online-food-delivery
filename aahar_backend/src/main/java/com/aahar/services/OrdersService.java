package com.aahar.services;

import java.util.List;

import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.RatingDTO;
import com.aahar.dto.AddOrderDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;
import com.aahar.entities.OrderStatus;

public interface OrdersService {

	List<CustomerOrderResponseDTO> getCustomerOrders(Long customerId);

	List<RestaurantOrderResponseDTO> getRestaurantOrders(Long restaurantId);

	void addOrder(AddOrderDTO orderDTO);
	
	
	
	
	
	
	
	
	void updateOrderStatus(Long orderId, OrderStatus newStatus);

	void updateRating(RatingDTO ratingDTO);

}
