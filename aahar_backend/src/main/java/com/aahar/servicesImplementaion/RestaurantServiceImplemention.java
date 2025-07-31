package com.aahar.servicesImplementaion;

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

	
	private RestaurantDao restaurantDao;
	private RestaurantOwnerDao ownerDao;
	private ModelMapper modelMapper;
	@Override
	public void addRestaurant(AddRestaurantDTO dto) {
		RestaurantOwner owner= ownerDao.findById(dto.getOwnerId()).orElseThrow(()-> new ResourceNotFoundException("Restaurant owner does not exist"));

		Restaurant restaurant= modelMapper.map(dto, Restaurant.class);
		restaurant.setRestaurantOwner(owner);
		owner.addRestaurant(restaurant);
		
		
		
		
	}
	@Override
	public void updateRestaurantById(RestaurantInfoDTO restaurantDTO) {
	

		Restaurant restaurant=restaurantDao.findById(restaurantDTO.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Restaurant Not Found"));
//		System.out.println(res);t
        modelMapper.map(restaurantDTO, restaurant);
        
	}
}

        
//		 public RestaurantInfoDTO updateRestaurantById(Long ownerId, Long restaurantId, RestaurantInfoDTO dto) {
//	    Restaurant restaurant = restaurantRepo.findById(restaurantId)
//	            .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
		
//	    if (!restaurant.getRestaurantOwner().getId().equals(ownerId)) {
//	        throw new IllegalArgumentException("Owner does not match the restaurant");
//	    }
//
//	    // Update fields
//	    restaurant.setRestaurantName(dto.getRestaurantName());
//	    restaurant.setRestauratDescription(dto.getRestauratDescription());
//	    restaurant.setVeg(dto.isVeg());
//	    restaurant.setAvgCost(dto.getAvgCost());
//	    restaurant.setOnline(dto.isOnline());
//
//	    // Update address if needed
//	    RestaurantAddress address = restaurant.getRestaurantAddress();
//	    if (address != null) {
//	        address.setAddress1(dto.getAddress1());
//	        address.setAddress2(dto.getAddress2());
//	        address.setAddress3(dto.getAddress3());
//	        address.setCity(dto.getCity());
//	        address.setPinCode(dto.getPinCode());
//	    }
//
//	    restaurantRepo.save(restaurant);
//
//	    // Prepare and return updated DTO
//	    RestaurantInfoDTO updatedDTO = new RestaurantInfoDTO();
//	    updatedDTO.setRestaurantId(restaurant.getId());
//	    updatedDTO.setOwnerId(ownerId);
//	    updatedDTO.setRestaurantName(restaurant.getRestaurantName());
//	    updatedDTO.setRestauratDescription(restaurant.getRestauratDescription());
//	    updatedDTO.setVeg(restaurant.isVeg());
//	    updatedDTO.setAvgCost(restaurant.getAvgCost());
//	    updatedDTO.setOnline(restaurant.isOnline());
//
//	    if (restaurant.getRestaurantAddress() != null) {
//	        updatedDTO.setAddress1(restaurant.getRestaurantAddress().getAddress1());
//	        updatedDTO.setAddress2(restaurant.getRestaurantAddress().getAddress2());
//	        updatedDTO.setAddress3(restaurant.getRestaurantAddress().getAddress3());
//	        updatedDTO.setCity(restaurant.getRestaurantAddress().getCity());
//	        updatedDTO.setPinCode(restaurant.getRestaurantAddress().getPinCode());
//	    }
//
//	    return updatedDTO;
//	}
// 
		
//
//	
//	@Autowired
//	private RestaurantDao restaurantRepo;
//	@Autowired
//	private RestaurantOwnerDao ownerRepo;
//	
//	
//	
//	 public RestaurantInfoDTO addRestaurant(AddRestaurantDTO dto) {
//	        RestaurantOwner owner = ownerRepo.findById(dto.getOwnerId())
//	                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + dto.getOwnerId()));
//
//	        Restaurant restaurant = new Restaurant(
//	                dto.getRestaurantName(),
//	                dto.getRestauratDescription(),
//	                dto.isVeg(),
//	                dto.getAvgCost(),
//	                dto.isOnline()
//	        );
//
//	        // Set address
//	        Restaurant address = new Restaurant();
//	        address.setRestaurant(restaurant);
//	        address.setAddress1(dto.getAddress1());
//	        address.setAddress2(dto.getAddress2());
//	        address.setAddress3(dto.getAddress3());
//	        address.setCity(dto.getCity());
//	        address.setPinCode(dto.getPinCode());
////	        address.setPhone(dto.getPhone());
//	        restaurant.setRestaurantAddress(address);
//
//	        // Set owner
//	        restaurant.setRestaurantOwner(owner);
//
//	        Restaurant saved = restaurantRepo.save(restaurant);
//
//	        // Convert to DTO
//	        RestaurantInfoDTO info = new RestaurantInfoDTO();
//	        info.setRestaurantId(saved.getId());
//	        info.setRestaurantName(saved.getRestaurantName());
//	        info.setRestauratDescription(saved.getRestauratDescription());
//	        info.setVeg(saved.isVeg());
//	        info.setAvgCost(saved.getAvgCost());
//	        info.setOnline(saved.isOnline());
//	        info.setRatingSum(saved.getRatingSum());
//	        info.setTotalRating(saved.getTotalRating());
//
//	        if (saved.getRestaurantAddress() != null) {
//	            info.setAddress1(saved.getRestaurantAddress().getAddress1());
//	            info.setAddress2(saved.getRestaurantAddress().getAddress2());
//	            info.setAddress3(saved.getRestaurantAddress().getAddress3());
//	            info.setCity(saved.getRestaurantAddress().getCity());
//	            info.setPinCode(saved.getRestaurantAddress().getPinCode());
////	            info.setPhone(saved.getRestaurantAddress().getPhone());
//	        }
//
//	        info.setOwnerId(owner.getId());
//	        info.setOwnerName(owner.getName());
//
//	        return info;
//	    }
//	 
//	 
//	 @Override
//	 public List<RestaurantInfoDTO> getRestaurantsByOwnerId(Long ownerId) {
//	     List<Restaurant> restaurants = restaurantRepo.findByRestaurantOwnerId(ownerId);
//
//	     return restaurants.stream().map(restaurant -> {
//	         RestaurantInfoDTO dto = new RestaurantInfoDTO();
//	         dto.setOwnerId(ownerId);
//	         dto.setRestaurantId(restaurant.getId());
//	         dto.setRestaurantName(restaurant.getRestaurantName());
//	         dto.setRestauratDescription(restaurant.getRestauratDescription());
//	         dto.setVeg(restaurant.isVeg());
//	         dto.setAvgCost(restaurant.getAvgCost());
//	         dto.setOnline(restaurant.isOnline());
//	         dto.setRatingSum(restaurant.getRatingSum());
//	         dto.setTotalRating(restaurant.getTotalRating());
//
//	         if (restaurant.getRestaurantAddress() != null) {
//	             dto.setAddress1(restaurant.getRestaurantAddress().getAddress1());
//	             dto.setAddress2(restaurant.getRestaurantAddress().getAddress2());
//	             dto.setAddress3(restaurant.getRestaurantAddress().getAddress3());
//	             dto.setCity(restaurant.getRestaurantAddress().getCity());
//	             dto.setPinCode(restaurant.getRestaurantAddress().getPinCode());
//	         }
//
//	         if (restaurant.getRestaurantOwner() != null) {
//	             dto.setOwnerName(restaurant.getRestaurantOwner().getName()); // assuming getName() exists
//	         }
//
//	         return dto;
//	     }).collect(Collectors.toList());
//	 }
//	 
//	 //Make the restaurant id hidden in frontend form 

//	 @Override
//	 public void deleteRestaurantById(Long ownerId, Long restaurantId) {
//	     // Find the restaurant
//	     Restaurant restaurant = restaurantRepo.findById(restaurantId)
//	             .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantId));
//
//	     // Check if the restaurant belongs to the given owner
//	     if (!restaurant.getRestaurantOwner().getId().equals(ownerId)) {
//	         throw new IllegalArgumentException("Unauthorized: Restaurant does not belong to owner ID: " + ownerId);
//	     }
//
//	     // Perform deletion
//	     restaurantRepo.delete(restaurant);
//	 }
//
//
//	 
//
//}
