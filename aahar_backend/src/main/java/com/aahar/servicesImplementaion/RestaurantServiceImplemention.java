package com.aahar.servicesImplementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.RestaurantDao;
import com.aahar.dao.RestaurantOwnerDao;
import com.aahar.dto.AddRestaurantDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantAddressDTO;
import com.aahar.dto.RestaurantInfoDTO;
import com.aahar.entities.OrderDetails;
import com.aahar.entities.Restaurant;

import com.aahar.entities.RestaurantOwner;
import com.aahar.services.RestaurantService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
//
@Service
@Transactional
@AllArgsConstructor
public class RestaurantServiceImplemention implements RestaurantService {

	//C:\Users\HP\OneDrive\Desktop\aahar\Online-food-delivery\aahar_backend\src\main
	private RestaurantDao restaurantDao;
	private RestaurantOwnerDao ownerDao;
	private ModelMapper modelMapper;
	@Override
	public ApiResponse addRestaurant(AddRestaurantDTO dto) {
		RestaurantOwner owner= ownerDao.findById(dto.getOwnerId()).orElseThrow(()-> new ResourceNotFoundException("Restaurant owner does not exist"));

		Restaurant restaurant= modelMapper.map(dto, Restaurant.class);
		restaurant.setRestaurantOwner(owner);
		owner.addRestaurant(restaurant);
		return new ApiResponse(true, "Restaurant Added Successfully",null);
		
		}
	@Override
	public ApiResponse updateRestaurantById(RestaurantInfoDTO restaurantDTO) {
	

		Restaurant restaurant=restaurantDao.findById(restaurantDTO.getId()).orElseThrow(()->new ResourceNotFoundException("Restaurant Not Found"));
  
        modelMapper.map(restaurantDTO, restaurant);
        return new ApiResponse(true,"Restaurant updated successfully" ,null);
        
	}
	
	
	@Override
	public ApiResponse updateRestaurantAddress(RestaurantAddressDTO addressdto) {
		Restaurant restaurant = restaurantDao.findById(addressdto.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Restaurant Not Found "));
		modelMapper.map(addressdto, restaurant);
		return new ApiResponse(true, "Restaurant Address updated successfully",null);
		
	}
	

	@Override
	public ApiResponse getRestaurantsByOwnerId(Long ownerId) {
	    List<Restaurant> dtoList = restaurantDao.findByRestaurantOwnerId(ownerId);
//	        .stream()
//	        .map(restaurant -> modelMapper.map(restaurant, RestaurantInfoDTO.class))
//	        .toList();
	    List<RestaurantInfoDTO> resp = new ArrayList<>();
	    for (Restaurant restaurant : dtoList) {
			Long ownerID  = restaurant.getRestaurantOwner().getId();
	    	RestaurantInfoDTO dto = modelMapper.map(restaurant, RestaurantInfoDTO.class);
	    	dto.setOwnerId(ownerID);
	    	dto.setOwnerName(restaurant.getRestaurantOwner().getName());
	    	resp.add(dto);
	    	
		}

	    return new ApiResponse(true, "Restaurants fetched successfully", resp);
	}
	
	
	@Override
	public ApiResponse deleteRestaurantById(Long ownerId, Long restaurantId) 
	{
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(()-> new ResourceNotFoundException("Restaurant not found with ID - "+restaurantId));
		if (!restaurant.getRestaurantOwner().getId().equals(ownerId)) {
	         throw new IllegalArgumentException("Unauthorized: Restaurant does not belong to owner ID: " + ownerId);
	     }
		
		 restaurantDao.delete(restaurant);
		 return new ApiResponse(true, "Restaurant deleted successfully", null);
	}
}

        

