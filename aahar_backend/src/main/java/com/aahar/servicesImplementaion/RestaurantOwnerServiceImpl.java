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
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {@Override
	public ApiResponse addOwner(RestaurantOwnerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantOwnerLoginResponseDTO loginOwner(RestaurantOwnerLoginRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
