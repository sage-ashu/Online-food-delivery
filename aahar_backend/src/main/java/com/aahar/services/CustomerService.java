package com.aahar.services;

import java.util.List;

import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.CustomerDTO;
import com.aahar.dto.CustomerLoginDTO;
import com.aahar.dto.CustomerRegisterDTO;
import com.aahar.dto.OrdersDTO;
import com.aahar.dto.UpdatePasswordDTO;
import com.aahar.dto.updateCustomerDTO;

public interface CustomerService {

	ApiResponse addAddress(Long Id, AddressDTO dto);

	ApiResponse addCustomer(CustomerDTO dto);

	List<OrdersDTO> allOrders(Long id);

	CustomerDTO customerProfile(Long id);

	ApiResponse deleteCustomer(Long customerId);

	ApiResponse updatePassword(Long id,UpdatePasswordDTO dto);

	ApiResponse updateProfile(Long customerId, updateCustomerDTO dto);
	
	ApiResponse registerCustomer(CustomerRegisterDTO dto);
	
	ApiResponse loginCustomer(CustomerLoginDTO dto);

	
	
	
	
	
	
	
	
}
