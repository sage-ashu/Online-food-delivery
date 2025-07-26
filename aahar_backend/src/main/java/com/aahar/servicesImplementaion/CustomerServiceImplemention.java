package com.aahar.servicesImplementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.CustomerDao;
import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.CustomerDTO;
import com.aahar.dto.OrdersDTO;
import com.aahar.entities.Customer;
import com.aahar.entities.CustomerAddress;
import com.aahar.entities.Orders;
import com.aahar.services.CustomerService;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CustomerServiceImplemention implements CustomerService {
	//now adding its CTOR DI
	public final CustomerDao customerDao;
	public final ModelMapper map;
	
	//here we are adding address to CustomerAddress table and also mapping it
	//with the customer (using a helper class written in entities.customer)
	@Override
	public ApiResponse addAddress(Long Id,AddressDTO dto) {
		Optional<Customer> customer=customerDao.findById(Id);
		if(customer.isPresent()) {
			Customer cus = customer.get();
			CustomerAddress AddressEntity = map.map(dto, CustomerAddress.class);
			cus.addAddress(AddressEntity);
			return new ApiResponse("Address add successfully.");
		}
		else
		return new ApiResponse("customer is not present");
	}
	
	//This API helps us to add the customer
	@Override
	public ApiResponse addCustomer(CustomerDTO dto) {
		Customer entity = map.map(dto, Customer.class);
		customerDao.save(entity);
		return new ApiResponse("Customer added successfully");
	}

	//This Api is used to fetch the list of all orders placed by customer
	@Override
	public List<OrdersDTO> allOrders(Long id) {
		Optional<Customer> cust =customerDao.findById(id);
		if(cust.isPresent()) {
			Customer customer =cust.get();
		List<Orders> orders=customer.getOrders();
		List<OrdersDTO> dto= orders.stream()
				.map(order->map.map(orders, OrdersDTO.class))
				.toList();
		return dto;
	}
		else
			return new ArrayList<>();
		}

}
