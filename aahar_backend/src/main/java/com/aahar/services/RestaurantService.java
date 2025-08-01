package com.aahar.services;

import java.util.List;

import com.aahar.dto.AddRestaurantDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantAddressDTO;
import com.aahar.dto.RestaurantInfoDTO;
//
//import com.aahar.dto.AddRestaurantDTO;
//import com.aahar.dto.ApiResponse;
//import com.aahar.dto.RestaurantInfoDTO;
//import com.aahar.dto.RestaurantOwnerLoginDTO;
//
public interface RestaurantService {

	ApiResponse addRestaurant(AddRestaurantDTO dto);

	ApiResponse updateRestaurantById(RestaurantInfoDTO restaurantDTO);

	ApiResponse updateRestaurantAddress(RestaurantAddressDTO addressdto);

	ApiResponse getRestaurantsByOwnerId(Long ownerId) ;

	ApiResponse deleteRestaurantById(Long ownerId, Long restaurantId);

	

//	List<RestaurantInfoDTO> getRestaurantsByOwnerId(Long ownerId);
//	 RestaurantInfoDTO updateRestaurantById(Long ownerId, Long restaurantId, RestaurantInfoDTO dto);
//	 void deleteRestaurantById(Long ownerId, Long restaurantId);
//
}
