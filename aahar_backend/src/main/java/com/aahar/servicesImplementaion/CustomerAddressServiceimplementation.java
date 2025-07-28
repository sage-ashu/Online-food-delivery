package com.aahar.servicesImplementaion;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.CustomerAddressDao;
import com.aahar.dto.CustomerAddressDTO;
import com.aahar.entities.Customer;
import com.aahar.entities.CustomerAddress;
import com.aahar.services.CustomerAddressService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerAddressServiceimplementation implements CustomerAddressService {
	private CustomerAddressDao customeraddressDao ;
	private final ModelMapper modelMapper;
	@Override
	public CustomerAddressDTO addCustomerAddress(CustomerAddressDTO dto) {
		 CustomerAddress address = modelMapper.map(dto, CustomerAddress.class);

	        // Set customer if required
	        if (dto.getCustomerId() != null) {
	            Customer customer = new Customer();
	            customer.setId(dto.getCustomerId());
	            address.setCustomer(customer);
	        }

	        CustomerAddress saved = customeraddressDao.save(address);
	        return modelMapper.map(saved, CustomerAddressDTO.class);
	    
	}

}
