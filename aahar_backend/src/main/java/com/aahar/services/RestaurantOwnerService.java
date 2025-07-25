package com.aahar.services;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantOwnerDTO;
import com.aahar.dto.RestaurantOwnerLoginRequestDTO;
import com.aahar.dto.RestaurantOwnerLoginResponseDTO;

public interface RestaurantOwnerService {

	ApiResponse addOwner(RestaurantOwnerDTO dto);
	// Inside RestaurantOwnerService.java

	RestaurantOwnerLoginResponseDTO loginOwner(RestaurantOwnerLoginRequestDTO dto);


}
