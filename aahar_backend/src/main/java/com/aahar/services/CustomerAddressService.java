package com.aahar.services;

import java.util.List;

import com.aahar.dto.CustomerAddressDTO;
import com.aahar.dto.CustomerAddressEditDTO;

public interface CustomerAddressService {

	CustomerAddressDTO addCustomerAddress(CustomerAddressDTO dto);

	List<CustomerAddressDTO> getAllCustomerAddresses();

	CustomerAddressEditDTO editCustomerAddress(Long id, CustomerAddressEditDTO dto);

	void deleteCustomerAddress(Long id);

	List<CustomerAddressDTO> getAddressesByCustomerId(Long customerId);

}
