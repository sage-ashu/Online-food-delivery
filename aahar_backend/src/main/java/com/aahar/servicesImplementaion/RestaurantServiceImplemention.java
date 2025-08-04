package com.aahar.servicesImplementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.CustomerAddressDao;
import com.aahar.dao.CustomerDao;
import com.aahar.dao.RestaurantDao;
import com.aahar.dao.RestaurantOwnerDao;
import com.aahar.dto.AddRestaurantDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantAddressDTO;
import com.aahar.dto.RestaurantInfoDTO;
import com.aahar.dto.RestaurantInfoForCustomerDTO;
import com.aahar.dto.RestaurantInfoForOwnerDTO;
import com.aahar.entities.Customer;
import com.aahar.entities.CustomerAddress;
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
	private CustomerDao customerDao;
	private ModelMapper modelMapper;
	private CustomerAddressDao customerAddressDao;
	
	@Override
	public ApiResponse saveOrUpdateRestaurant(AddRestaurantDTO dto) {
	    RestaurantOwner owner = ownerDao.findById(dto.getOwnerId())
	        .orElseThrow(() -> new ResourceNotFoundException("Restaurant owner does not exist"));

	    Optional<Restaurant> optionalRestaurant = restaurantDao.findByRestaurantOwnerId(dto.getOwnerId());

	    Restaurant restaurant;
	    if (optionalRestaurant.isPresent()) {
	        restaurant = optionalRestaurant.get();
	        // Update existing restaurant
	        restaurant.setRestaurantName(dto.getRestaurantName());
	        restaurant.setRestauratDescription(dto.getRestaurantDescription());
	        restaurant.setVeg(dto.isVeg());
	        restaurant.setAvgCost(dto.getAvgCost());
	        restaurant.setOnline(dto.isOnline());
	        restaurant.setPhoneNo(dto.getPhoneNo());
	        restaurant.setAddress1(dto.getAddress1());
	        restaurant.setAddress2(dto.getAddress2());
	        restaurant.setAddress3(dto.getAddress3());
	        restaurant.setCity(dto.getCity());
	        restaurant.setState(dto.getState());
	        restaurant.setPinCode(dto.getPinCode());
	        restaurant.setLatitude(dto.getLatitude());
	        restaurant.setLongitude(dto.getLongitude());

	        return new ApiResponse(true, "Restaurant updated successfully", null);
	    } else {
	        // Create new restaurant
	        restaurant = modelMapper.map(dto, Restaurant.class);
	        restaurant.setRestaurantOwner(owner);
	        owner.addRestaurant(restaurant);

	        return new ApiResponse(true, "Restaurant added successfully", null);
	    }
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
	    Optional<Restaurant> optionalRestaurant = restaurantDao.findByRestaurantOwnerId(ownerId);

	    if (optionalRestaurant.isPresent()) {
	        Restaurant restaurant = optionalRestaurant.get();
	        
	        RestaurantInfoForOwnerDTO dto = new RestaurantInfoForOwnerDTO();
	        dto.setRestaurantName(restaurant.getRestaurantName());
	        dto.setRestaurantDescription(restaurant.getRestauratDescription()); // Manual mapping
	        dto.setVeg(restaurant.isVeg());
	        dto.setAvgCost(restaurant.getAvgCost());
	        dto.setOnline(restaurant.isOnline());
	        dto.setPhoneNo(restaurant.getPhoneNo());
	        dto.setAddress1(restaurant.getAddress1());
	        dto.setAddress2(restaurant.getAddress2());
	        dto.setAddress3(restaurant.getAddress3());
	        dto.setCity(restaurant.getCity());
	        dto.setState(restaurant.getState());
	        dto.setPinCode(restaurant.getPinCode());
	        dto.setLatitude(restaurant.getLatitude());
	        dto.setLongitude(restaurant.getLongitude());

	        return new ApiResponse(true, "Restaurant fetched successfully", dto);
	    } else {
	        return new ApiResponse(false, "No restaurant found", null);
	    }
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
	@Override
	public ApiResponse getRestaurantsForCustomer(Long restaurantId) {
	    Restaurant entity = restaurantDao.findById(restaurantId)
	        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

	    RestaurantInfoForCustomerDTO dto = modelMapper.map(entity, RestaurantInfoForCustomerDTO.class);
	    dto.setRating(entity.getRating());

	    return new ApiResponse(true, "Restaurant fetched successfully", dto);
	}
	@Override
	public ApiResponse getRestaurantInSameCityAsCustomer(Long customerAddressId) {
		CustomerAddress customerAddress= customerAddressDao.findById(customerAddressId).orElseThrow(()-> new ResourceNotFoundException("Customer Address not found with Id - "+customerAddressId));
		String city = customerAddress.getCity();
		List<Restaurant> restaurants = restaurantDao.findByCity(city);
		List<RestaurantInfoForCustomerDTO> dtoList = new ArrayList<>();
		for(Restaurant res : restaurants) {
			
			dtoList.add(modelMapper.map(res, RestaurantInfoForCustomerDTO.class));
			dtoList.getLast().setRating(res.getRating());;
		}
		
	    	    return new ApiResponse(true, "Restaurants in same city as customer", dtoList);
	       
	    }
	@Override
	public ApiResponse updateRestaurantStatus(Long restaurantId, boolean status) {
		Restaurant restaurant=restaurantDao.findById(restaurantId).orElseThrow(()-> new ResourceNotFoundException("Restaurant not found"));
		restaurant.setOnline(status);
		return new ApiResponse(true, status?"Restauarnt ready to take orders":"Restaurant offline", null);
	}
	
    public Restaurant getRestaurantByOwnerId(Long ownerId) {
        return restaurantDao.findByRestaurantOwnerId(ownerId)
                .orElse(null);
    }
}



        

