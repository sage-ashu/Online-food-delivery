package com.aahar.servicesImplementaion;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.RestaurantOwnerDao;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantOwnerDTO;
import com.aahar.entities.RestaurantOwner;
import com.aahar.services.RestaurantOwnerService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {
//ctor based DI
	public final RestaurantOwnerDao restaurantOwnerDao;
	public final ModelMapper map;
	@Override
	public ApiResponse addOwner(RestaurantOwnerDTO dto) {
		// TODO Auto-generated method stub
		RestaurantOwner entity = map.map(dto, RestaurantOwner.class);
		restaurantOwnerDao.save(entity);
		
		return new ApiResponse("Owner successfully registered");
	}
	
	//addOwner
	

}
