package com.aahar.servicesImplementaion;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.RestaurantOwnerDao;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantOwnerDTO;
import com.aahar.dto.RestaurantOwnerLoginRequestDTO;
import com.aahar.dto.RestaurantOwnerLoginResponseDTO;
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
		System.out.println(dto);
		RestaurantOwner entity = map.map(dto, RestaurantOwner.class);
		System.out.println(entity);
		restaurantOwnerDao.save(entity);
		
		return new ApiResponse("Owner successfully registered");
	}
	
	//Api for login of restaurant 
	@Override
	public RestaurantOwnerLoginResponseDTO loginOwner(RestaurantOwnerLoginRequestDTO dto) {
	    // Find owner by email
	    RestaurantOwner owner = restaurantOwnerDao.findByEmail(dto.getEmail())
	        .orElseThrow(() -> new RuntimeException("Invalid email or password"));
	    
	    System.out.println(owner);
	    
	    // Match password (You should hash and check in real apps)
	    if (!owner.getPassword().equals(dto.getPassword())) {
	        throw new RuntimeException("Invalid email or password");
	    }
	    System.out.println(owner);
	    
	    // Map entity to response DTO
	    return map.map(owner, RestaurantOwnerLoginResponseDTO.class);
	}

	

}
