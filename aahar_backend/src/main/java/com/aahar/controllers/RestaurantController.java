package com.aahar.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.config.JwtUtil;
import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dto.AddRestaurantDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantAddressDTO;
import com.aahar.dto.RestaurantInfoDTO;
import com.aahar.services.RestaurantService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/owner/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final JwtUtil jwtUtil;
    
    
    //This api adds the restaurant info , if already presents then it updates it 
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdateRestaurant(@RequestBody AddRestaurantDTO dto) {
        try {
        	System.out.println(dto.toString());
            ApiResponse response = restaurantService.saveOrUpdateRestaurant(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Failed to save/update restaurant: " + e.getMessage()));
        }
    }

    
    //2.Update Restaurant by ownerId and RestaurantId
    
  @PutMapping
  public ResponseEntity<?> updateRestaurantById(@RequestBody RestaurantInfoDTO restaurantDTO) 
  {	

      try {
           restaurantService.updateRestaurantById(restaurantDTO);
          return ResponseEntity.ok("Restaurant updated successfully");
          }catch (IllegalArgumentException e) 
      {
          ApiResponse response = new ApiResponse(false, e.getMessage(), null);
          return ResponseEntity.badRequest().body(response);
          
      } catch (Exception e) {
          ApiResponse response = new ApiResponse(false, "Internal server error", null);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
  }
  
  //3. update restaurant address for owner
  @PutMapping("/updateAddress")
  public ResponseEntity<?> updateRestaurantAddressById(@RequestBody RestaurantAddressDTO addressdto)
  {
	  try {
		  restaurantService.updateRestaurantAddress(addressdto);
		  return ResponseEntity.ok("Restaurant Address updated successfully");
	  }catch (IllegalArgumentException e)
	  {
		  ApiResponse response = new ApiResponse(false, e.getMessage(), null);
          return ResponseEntity.badRequest().body(response);
	  }catch (Exception e)
	  {
		  ApiResponse response = new ApiResponse(false, "Internal server error", null);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
  }
  

  @GetMapping("/{ownerId}")
  public ResponseEntity<?> restaurantDetailsForOwner(@PathVariable Long ownerId) {
      System.out.println("Restaurant owner id is : " + ownerId);
      try {
          ApiResponse restaurant = restaurantService.getRestaurantsByOwnerId(ownerId);
          return ResponseEntity.ok(restaurant); // ✅ FIXED
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ApiResponse(false, "Error: " + e.getMessage()));
      }
  }


   
    //5.Delete Restaurant By Id
  @DeleteMapping("/{ownerId}/{restaurantId}")
  public ResponseEntity<?> deleteRestaurant(@PathVariable Long ownerId, @PathVariable Long restaurantId)
  {
	   try 
	   {
		 restaurantService.deleteRestaurantById(ownerId,restaurantId);
		 ApiResponse response = new ApiResponse(true, "Restaurant deleted successfully",null);
		 return ResponseEntity.ok(response);
	   }catch (IllegalArgumentException e) {
	    ApiResponse response =new ApiResponse(false, e.getMessage(), null);
	    return ResponseEntity.badRequest().body(response);
	   }catch (Exception e) {
		ApiResponse response = new ApiResponse(false,"Error fetching restaurants"+ e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	   }
  }
   
   //6.Get Restaurant Details for Customer
   @GetMapping("/restaurant")
   public ResponseEntity<?> getRestaurantsForCustomer(HttpServletRequest request) {
	   String token = jwtUtil.extractTokenFromRequest(request);
	   Long ownerId = jwtUtil.extractId(token);
       try {
           ApiResponse response = restaurantService.getRestaurantsForCustomer(ownerId);
           return ResponseEntity.ok(response);
       } catch (IllegalArgumentException e) {
           ApiResponse response = new ApiResponse(false, e.getMessage(), null);
           return ResponseEntity.badRequest().body(response);
       } catch (Exception e) {
           ApiResponse response = new ApiResponse(false, "Error fetching restaurants: " + e.getMessage(), null);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
   }

//    7.get all restaurants in the same city as user
   @GetMapping("/restaurant/{customerAddressId}/city")
   public ResponseEntity<?> getRestaurantsInSameCityAsCustomer(@PathVariable Long customerAddressId)
   {
	   try {
		   ApiResponse response=restaurantService.getRestaurantInSameCityAsCustomer(customerAddressId);
		   return ResponseEntity.ok(response);
	   }catch (ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse(false, e.getMessage(), null));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ApiResponse(false, "Error: " + e.getMessage(), null));
	        }
	   
   }
   


//8. update restaurant availability status
    @PutMapping("/updateStatus/{status}")
    public ResponseEntity<?> updateRestaurantStatus(HttpServletRequest request, boolean status )
    {  
    	 String token = jwtUtil.extractTokenFromRequest(request);
  	   Long ownerId = jwtUtil.extractId(token);
    	try {
    		ApiResponse response=restaurantService.updateRestaurantStatus(ownerId,status);
    		return ResponseEntity.ok(response);
    	}catch (ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse(false, e.getMessage(), null));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ApiResponse(false, "Error: " + e.getMessage(), null));
	        }
    	
    }
}
	
   


// 0. add restaurant by owner
// 1. get all restaurants in the same city as user
// 2. get restaurant details for owner
// 3. get restaurant details for customer
// 4. update restaurant details for owner
// 5. update restaurant address for owner
// 6. delete restaurant
// 7. update restaurant availability status
