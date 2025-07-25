package com.aahar.services;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantOwnerDTO;

public interface RestaurantOwnerService {

	ApiResponse addOwner(RestaurantOwnerDTO dto);

}
