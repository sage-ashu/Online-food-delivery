package com.aahar.servicesImplementaion;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.aahar.dao.DishDao;
import com.aahar.dao.OrderDetailsDao;
import com.aahar.dao.OrdersDao;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.OrderDetailsDTO;
import com.aahar.entities.Dish;
import com.aahar.entities.OrderDetails;
import com.aahar.entities.Orders;
import com.aahar.services.OrderDetailsService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailsServiceImplementation implements OrderDetailsService{

	private final OrderDetailsDao orderDetailsDao;
	private final OrdersDao ordersDao;
	private final DishDao dishDao;
	private final ModelMapper modelMapper;
	@Override
	public ApiResponse addOrderDetails(Long orderId, Long dishId, OrderDetailsDTO dto) {
		
		// Fetch associated entities
		Orders order=ordersDao.findById(orderId)
				.orElseThrow(()-> new RuntimeException("Order not found with ID: "+ orderId));
		
		 // Map DTO to Entity
		Dish dish =dishDao.findById(dishId)
				.orElseThrow(()-> new RuntimeException("Dish not found with ID: "+dishId));
		
		OrderDetails orderDetails=modelMapper.map(dto, OrderDetails.class);
		orderDetails.setOrders(order);
		orderDetails.setDish(dish);
		
		//save
		orderDetailsDao.save(orderDetails);
		
		return new ApiResponse(true, "Order details added successfully");
		
	}

}
