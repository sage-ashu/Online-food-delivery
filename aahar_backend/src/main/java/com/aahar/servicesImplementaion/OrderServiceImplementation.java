package com.aahar.servicesImplementaion;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.OrdersDao;
import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;
import com.aahar.entities.Orders;
import com.aahar.services.OrdersService;

import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImplementation implements OrdersService {
	private OrdersDao ordersDao;
	private ModelMapper modelMapper;

	@Override
	public List<CustomerOrderResponseDTO> getCustomerOrders(Long customerId) {
		// TODO Auto-generated method stub
		List<Orders> orders = ordersDao.findAll();
		List<CustomerOrderResponseDTO> customerOrders = new ArrayList<>();
		for(Orders o : orders) {
			if(o.getCustomer().getId()==customerId) {
//				CustomerOrderResponseDTO dto = new CustomerOrderResponseDTO();
//				dto.setRestaurantName(o.getRestaurant().getRestaurantName());   
				
				customerOrders.add(modelMapper.map(o, CustomerOrderResponseDTO.class));
				customerOrders.get(customerOrders.size()-1).setRestaurantName(o.getRestaurant().getRestaurantName());
			}
		}
		return customerOrders;
	}

	@Override
	public List<RestaurantOrderResponseDTO> getRestaurantOrders(Long restaurantId) {
		// TODO Auto-generated method stub
		List<Orders> orders = ordersDao.findAll();
		List<RestaurantOrderResponseDTO> restaurantOrders = new ArrayList<>();
		for(Orders o : orders) {
			if(o.getRestaurant().getId()==restaurantId) {
//				CustomerOrderResponseDTO dto = new CustomerOrderResponseDTO();
//				dto.setRestaurantName(o.getRestaurant().getRestaurantName());   
				
				restaurantOrders.add(modelMapper.map(o, RestaurantOrderResponseDTO.class));
//				restaurantOrders.get(restaurantOrders.size()-1).setRestaurantName(o.getRestaurant().getRestaurantName());
			}
		}
		return restaurantOrders;
	}

}
