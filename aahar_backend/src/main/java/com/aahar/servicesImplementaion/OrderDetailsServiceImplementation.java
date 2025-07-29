package com.aahar.servicesImplementaion;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.DishDao;
import com.aahar.dao.OrderDetailsDao;
import com.aahar.dao.OrdersDao;
import com.aahar.dto.AddOrderDTO;
import com.aahar.dto.AddOrderDetailsDTO;
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
	public void addOrderDetails(Long orderId, List<AddOrderDetailsDTO> orderDetails) {
		
		Orders order=ordersDao.findById(orderId)
				.orElseThrow(()-> new RuntimeException("Order not found with ID: "+ orderId));
		for(AddOrderDetailsDTO orderDetail : orderDetails) {
			Dish dish = dishDao.findById(orderDetail.getDishId()).orElseThrow(()->new ResourceNotFoundException("Invalid Dish ID"));
			order.addOrderDetail(modelMapper.map(orderDetail, OrderDetails.class));
			dish.addOrderDetail(modelMapper.map(orderDetail, OrderDetails.class));
			
		}
//		Orders order=ordersDao.findById(orderId)
//				.orElseThrow(()-> new RuntimeException("Order not found with ID: "+ orderId));
//		
//		Dish dish =dishDao.findById(dishId)
//				.orElseThrow(()-> new RuntimeException("Dish not found with ID: "+dishId));
//		
//		OrderDetails orderDetails=modelMapper.map(dto, OrderDetails.class);
//		orderDetails.setOrders(order);
//		orderDetails.setDish(dish);
//		orderDetailsDao.save(orderDetails);
//		
//		return new ApiResponse(true, "Order details added successfully");
		
	}

}
