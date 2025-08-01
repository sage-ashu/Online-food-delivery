package com.aahar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dto.AddRestaurantDTO;
import com.aahar.dto.ApiResponse;
import com.aahar.dto.RestaurantAddressDTO;
import com.aahar.dto.RestaurantInfoDTO;
import com.aahar.services.RestaurantService;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/owner/restaurant")
public class RestaurantController {
	
	
	
    @Autowired
    private RestaurantService restaurantService;

    
    // 1. Add restaurant by ownerId
    @PostMapping("/add")
    public ResponseEntity<?> addRestaurant(@RequestBody AddRestaurantDTO dto) {
        try {
            restaurantService.addRestaurant(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant Added");

        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to add restaurant: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
  
  //4. get restaurant details for owner
   @GetMapping("/{ownerId}")
   public ResponseEntity<?> restaurantDetailsForOwner(@PathVariable Long ownerId)
   {
	   try 
	   {
		 ApiResponse  restaurants = restaurantService.getRestaurantsByOwnerId(ownerId);
		 if (restaurants.isEmpty())
		 {
          ApiResponse response = new ApiResponse(false, "No restaurants found for owner ID: " + ownerId);
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
         }
		 ApiResponse response = new ApiResponse(true, "Restaurants for owner ID " + ownerId, restaurants);
         return ResponseEntity.ok(response);
	   }catch (RuntimeException e)
	   {
         ApiResponse response = new ApiResponse(false, "Error fetching restaurants: " + e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
   @GetMapping("/restaurant/{restaurantId}")
   public ResponseEntity<?> getRestaurantsForCustomer(@PathVariable Long restaurantId) {
       try {
           ApiResponse response = restaurantService.getRestaurantsForCustomer(restaurantId);
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
    @PutMapping("/updateStatus/{restaurantId}/{status}")
    public ResponseEntity<?> updateRestaurantStatus(@PathVariable Long restaurantId, boolean status )
    {  
    	try {
    		ApiResponse response=restaurantService.updateRestaurantStatus(restaurantId,status);
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
