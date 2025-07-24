package com.aahar.services;

import com.aahar.dto.AddressDTO;
import com.aahar.dto.ApiResponse;

public interface CustomerService {

	ApiResponse addAddress(Long Id, AddressDTO dto);

}
