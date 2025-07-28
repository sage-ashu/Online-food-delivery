package com.aahar.servicesImplementaion;
import java.util.stream.Collectors;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.RestaurantAddressDao;

import com.aahar.dto.RestaurantAddressDTO;
import com.aahar.entities.Restaurant;
import com.aahar.entities.RestaurantAddress;
import com.aahar.services.RestaurantAddressService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RestaurantAddressServiceImplementation implements RestaurantAddressService{
	private final RestaurantAddressDao restaurantaddressDao;
	private final ModelMapper modelMapper;
	
	@Override
	public List<RestaurantAddressDTO> getAllRestaurantAddress() {
		return restaurantaddressDao.findAll()
				.stream()
				 .map(address -> modelMapper.map(address, RestaurantAddressDTO.class))
	                .collect(Collectors.toList());
	}
	 @Override
	    public RestaurantAddressDTO addRestaurantAddress(RestaurantAddressDTO dto) {
	        // Convert DTO → Entity
	        RestaurantAddress entity = modelMapper.map(dto, RestaurantAddress.class);

	        // If restaurantId exists, you need to set Restaurant object manually
	        if (dto.getRestaurantId() != null) {
	            Restaurant restaurant = new Restaurant();
	            restaurant.setId(dto.getRestaurantId());
	            entity.setRestaurant(restaurant);
	        }

	        RestaurantAddress saved = restaurantaddressDao.save(entity);
	        return modelMapper.map(saved, RestaurantAddressDTO.class);
	    }
	@Override
	public RestaurantAddressDTO updateRestaurantAddress(Long id, RestaurantAddressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
