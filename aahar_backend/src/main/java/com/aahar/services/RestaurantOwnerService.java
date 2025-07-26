package com.aahar.services;

import com.aahar.dto.RestaurantOwnerUpdateDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.PasswordUpdateDTO;
import com.aahar.dto.RestaurantOwnerLoginDTO;
import com.aahar.dto.RestaurantOwnerRegistrationDTO;

public interface RestaurantOwnerService {
	ApiResponse registerOwner(RestaurantOwnerRegistrationDTO dto);
    ApiResponse updateOwnerDetails(Long ownerId, RestaurantOwnerUpdateDTO dto);
    ApiResponse loginOwner(RestaurantOwnerLoginDTO dto);
    ApiResponse updateOwnerPassword(Long ownerId, PasswordUpdateDTO dto);
    ApiResponse getOwnerDetails(Long ownerId);


}
