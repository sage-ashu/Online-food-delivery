package com.aahar.servicesImplementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.CustomerDao;
import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.CustomerDTO;
import com.aahar.dto.CustomerLoginDTO;
import com.aahar.dto.CustomerRegisterDTO;
import com.aahar.dto.CustomerResponseDTO;
import com.aahar.dto.OrdersDTO;
import com.aahar.dto.UpdatePasswordDTO;
import com.aahar.dto.updateCustomerDTO;
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
	private PasswordEncoder passwordEncoder;
	
	//here we are adding address to CustomerAddress table and also mapping it
	//with the customer (using a helper class written in entities.customer)
	@Override
	public ApiResponse addAddress(Long Id,AddressDTO dto) {
		Optional<Customer> customer=customerDao.findById(Id);
		if(customer.isPresent()) {
			Customer cus = customer.get();
			CustomerAddress AddressEntity = map.map(dto, CustomerAddress.class);
			cus.addAddress(AddressEntity);
			return new ApiResponse(true,"Address add successfully.");
		}
		else
		return new ApiResponse(false,"customer is not present");
	}
	
	//This API helps us to add the customer
	@Override
	public ApiResponse addCustomer(CustomerDTO dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		Customer entity = map.map(dto, Customer.class);
		entity.setPassword(encodedPassword);
		customerDao.save(entity);
		return new ApiResponse(true,"Customer added successfully");
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

	@Override
	public CustomerDTO customerProfile(Long id) {
		Customer entity =customerDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("customer not found"));
		
			return map.map(entity, CustomerDTO.class);
		
	}

	@Override
	public ApiResponse deleteCustomer(Long customerId) {
		customerDao.deleteById(customerId);
		return new ApiResponse(true, "customer deleted successfully");
	}

	@Override
	public ApiResponse updatePassword(Long id, UpdatePasswordDTO dto) {
		Customer entity = customerDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("customer not found"));
		if(entity.getPassword().contains(dto.oldPassword)) {
			entity.setPassword(dto.getNewPassword());
			customerDao.save(entity);
			return new ApiResponse(true, "Password updated successfully");
		}
		else {
		return new ApiResponse(false, "Please enter correct password");
		}
	}

	@Override
	public ApiResponse updateProfile(Long customerId, updateCustomerDTO dto) {
		Customer entity = customerDao.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("customer not found"));
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		customerDao.save(entity);
		return new ApiResponse(true,"customer details updated successfully");
	}
	
	
	
	//To register a customer
	@Override
	public ApiResponse registerCustomer(CustomerRegisterDTO dto) {

	    Customer found = customerDao.findByEmail(dto.getEmail());
	    if (found == null) {
	        Customer c = map.map(dto, Customer.class);
	        Customer saved = customerDao.save(c);
	        return new ApiResponse(true, "Customer Registered successfully", saved);
	    } else {
	        return new ApiResponse(false, "Email is already registered");
	    }
	}

	
	//To login a customer 
	@Override
	public ApiResponse loginCustomer(CustomerLoginDTO dto) {

	    Customer found = customerDao.findByEmail(dto.getEmail());
	    if (found == null) {
	        return new ApiResponse(false, "Wrong email or password");
	    } else {
	        // Convert to DTO that does not have lazy-loaded fields
	        CustomerResponseDTO responseDTO = map.map(found, CustomerResponseDTO.class);
	        return new ApiResponse(true, "Customer logged in successfully", responseDTO);
	    }
	}


}
