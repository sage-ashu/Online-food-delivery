package com.aahar.services;

import java.util.List;

import com.aahar.dto.AddRestaurantDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantInfoDTO;
import com.aahar.dto.RestaurantOwnerLoginDTO;

public interface RestaurantService {
	RestaurantInfoDTO addRestaurant(AddRestaurantDTO dto);
	List<RestaurantInfoDTO> getRestaurantsByOwnerId(Long ownerId);
	 RestaurantInfoDTO updateRestaurantById(Long ownerId, Long restaurantId, RestaurantInfoDTO dto);
	 void deleteRestaurantById(Long ownerId, Long restaurantId);

}
