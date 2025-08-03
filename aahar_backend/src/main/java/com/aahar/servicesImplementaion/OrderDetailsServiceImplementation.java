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
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrderDetailsServiceImplementation implements OrderDetailsService{

	private final OrderDetailsDao orderDetailsDao;
	private final OrdersDao ordersDao;
	private final DishDao dishDao;
	private final ModelMapper modelMapper;
	@Override
	public void addOrderDetails(Long orderId, List<AddOrderDetailsDTO> orderDetails) {
	
		Orders order = ordersDao.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order ID invalid "));
		for(AddOrderDetailsDTO dto : orderDetails) {
			Dish dish = dishDao.findById(dto.getDishId()).orElseThrow(()-> new ResourceNotFoundException("Invalid dish Id"));
			OrderDetails orderDetail = new OrderDetails(dto.getQuantity());
			dish.addOrderDetail(orderDetail);
			order.addOrderDetail(orderDetail);
		}
	}

}
