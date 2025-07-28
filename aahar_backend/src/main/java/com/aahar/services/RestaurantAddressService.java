package com.aahar.services;

import java.util.List;

import com.aahar.dto.AddressDTO;
import com.aahar.dto.RestaurantAddressDTO;

public interface RestaurantAddressService {

	List<RestaurantAddressDTO> getAllRestaurantAddress();

	RestaurantAddressDTO addRestaurantAddress(RestaurantAddressDTO dto);

	RestaurantAddressDTO updateRestaurantAddress(Long id, RestaurantAddressDTO dto);

}
