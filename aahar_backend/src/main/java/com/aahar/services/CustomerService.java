package com.aahar.services;

import java.util.List;

import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.CustomerDTO;
import com.aahar.dto.OrdersDTO;
import com.aahar.entities.Orders;

public interface CustomerService {

	ApiResponse addAddress(Long Id, AddressDTO dto);

	ApiResponse addCustomer(CustomerDTO dto);

	List<OrdersDTO> allOrders(Long id);

}
