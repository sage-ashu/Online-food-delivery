package com.aahar.servicesImplementaion;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.CustomerAddressDao;
import com.aahar.dao.DishDao;
import com.aahar.dao.OrdersDao;
import com.aahar.dao.RestaurantDao;
import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.AddOrderDTO;
import com.aahar.dto.AddOrderDetailsDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;
import com.aahar.entities.Customer;
import com.aahar.entities.CustomerAddress;
import com.aahar.entities.Dish;
import com.aahar.entities.Orders;
import com.aahar.entities.Restaurant;
import com.aahar.services.DistanceService;
import com.aahar.services.OrdersService;

import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImplementation implements OrdersService {
	private OrdersDao ordersDao;
	private ModelMapper modelMapper;
	private final DistanceService distanceService;
	private CustomerAddressDao customerAddressDao;
	private RestaurantDao restaurantDao;
	private DishDao dishDao;

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

	@Override
	public void addOrder(AddOrderDTO orderDTO) {
		// TODO Auto-generated method stub
		CustomerAddress customerAddress = customerAddressDao.findById(orderDTO.getCustomerAddressId()).orElseThrow(()-> new ResourceNotFoundException("Invalid address ID"));
		Customer customer = customerAddress.getCustomer();
		Restaurant restaurant = restaurantDao.findById(orderDTO.getReaturantId()).orElseThrow(()->new ResourceNotFoundException("Invalid restauarnt ID"));
		int distanceInMeters = distanceService.getDistanceInMeters(restaurant.getLatitude(), restaurant.getLongitude(), customerAddress.getLatitude(), customerAddress.getLongitude());
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setDeliveryDistance(distanceInMeters);
		Double deliveryCharge = distanceInMeters*0.005;
		order.setDeliveryCharge(deliveryCharge);
		List<AddOrderDetailsDTO> orderDetails = orderDTO.getDetails();
		double orderAmount = 0;
		for(AddOrderDetailsDTO detail : orderDetails) {
			Dish dish = dishDao.findById(detail.getDishId()).orElseThrow(()-> new ResourceNotFoundException("Dish not found"));
			orderAmount += dish.getDishPrice()*detail.getQuantity();
		}
		order.setOrderAmount(orderAmount);
		order.setOrderTotal(orderAmount+deliveryCharge);
		customer.addOrders(order);
	}

}
