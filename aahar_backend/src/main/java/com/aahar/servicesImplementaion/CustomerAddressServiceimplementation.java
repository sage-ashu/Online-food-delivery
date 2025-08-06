   package com.aahar.servicesImplementaion;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.CustomerAddressDao;
import com.aahar.dao.CustomerDao;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.CustomerAddressDTO;
import com.aahar.dto.CustomerAddressEditDTO;
import com.aahar.entities.Customer;
import com.aahar.entities.CustomerAddress;
import com.aahar.services.CustomerAddressService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerAddressServiceimplementation implements CustomerAddressService {
	private CustomerAddressDao customeraddressDao ;
	private CustomerDao customerDao;
	private final ModelMapper modelMapper;
	@Override
	public ApiResponse addCustomerAddress(Long customerId,CustomerAddressDTO dto) {
	    CustomerAddress address = modelMapper.map(dto, CustomerAddress.class);
	    Customer customer = customerDao.findById(customerId)
	    		.orElseThrow(()-> new ResourceNotFoundException("Customer does not exist"));

	    customer.addAddress(address);
	    return new ApiResponse(true, "Address added successfully");
	}

	@Override
	 public List<CustomerAddressDTO> getAllCustomerAddresses() {
        return customeraddressDao.findAll()
                .stream()
                .map(address -> modelMapper.map(address, CustomerAddressDTO.class))
                .collect(Collectors.toList());
    }

	@Override
	public CustomerAddressEditDTO editCustomerAddress(Long id, CustomerAddressEditDTO dto) {
	    CustomerAddress existing = customeraddressDao.findById(id)
	            .orElseThrow(() -> new RuntimeException("Address not found"));

	    // Map editable fields only
	    modelMapper.map(dto, existing);

	    // Do NOT modify customerId during edit

	    CustomerAddress updated = customeraddressDao.save(existing);

	    return modelMapper.map(updated, CustomerAddressEditDTO.class);
	}
	@Override
	public void deleteCustomerAddress(Long id) {
		  if (!customeraddressDao.existsById(id)) {
	            throw new RuntimeException("Customer Address not found with id: " + id);
	        }
	        customeraddressDao.deleteById(id);
	    }
	@Override
	public List<CustomerAddressDTO> getAddressesByCustomerId(Long customerId) {
		List<CustomerAddress> addresses = customeraddressDao.findByCustomerId(customerId);
        return addresses.stream()
                .map(address -> modelMapper.map(address, CustomerAddressDTO.class))
                .collect(Collectors.toList());
    }


}
