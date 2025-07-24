package com.aahar.servicesImplementaion;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.CustomerDao;
import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.entities.Address;
import com.aahar.entities.Customer;
import com.aahar.services.CustomerService;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CustomerServiceImplemention implements CustomerService {
//now adding its CTOR DI
	public final CustomerDao customerDao;
	public final ModelMapper map;
	@Override
	public ApiResponse addAddress(Long Id,AddressDTO dto) {
		Optional<Customer> customer=customerDao.findById(Id);
		if(customer.isPresent()) {
			Customer cus = customer.get();
			Address AddressEntity = map.map(dto, Address.class);
			cus.addAddress(AddressEntity);
			return new ApiResponse("Address add successfully.");
		}
		return null;
	}

}
