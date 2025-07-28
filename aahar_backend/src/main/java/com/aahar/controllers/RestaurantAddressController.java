//package com.aahar.controllers;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.aahar.dto.RestaurantAddressDTO;
//import com.aahar.services.RestaurantAddressService;
//
//import lombok.AllArgsConstructor;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/api/restaurant-address")
//public class RestaurantAddressController {
//	
//	private final RestaurantAddressService restaurantaddressservice;
//	//0.Get Address
//	@GetMapping
//	public ResponseEntity<?> getAllRestaurantAddress() {
//		System.out.println("Get all Restaurants Address");
//		List<RestaurantAddressDTO> restaurantaddress =restaurantaddressservice.getAllRestaurantAddress();
//		if(restaurantaddress.isEmpty())
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		return ResponseEntity.ok(restaurantaddress);
//	}
//	
//	 @PostMapping
//	 public String postMethodName(@RequestBody String entity) {
//	 	//TODO: process POST request
//	 	
//	 	return entity;
//	 }
//	 
//	    public ResponseEntity<RestaurantAddressDTO> addRestaurantAddress(@RequestBody RestaurantAddressDTO dto) {
//	        RestaurantAddressDTO saved = restaurantaddressservice.addRestaurantAddress(dto);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//	    }
//	    
////	    @PutMapping("/{id}")
////	    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
////	    	//TODO: process PUT request
////	    	
////	    	return entity;
////	    }("/{id}")
////	    public ResponseEntity<RestaurantAddressDTO> updateRestaurantAddress(
////	            @PathVariable Long id,
////	            @RequestBody RestaurantAddressDTO restaurantAddressDTO) {
////
////	        RestaurantAddressDTO updated = restaurantaddressService.updateRestaurantAddress(id, restaurantAddressDTO);
////	        return ResponseEntity.ok(updated);
////	    }
////	
//	//1. Add Address
//	
//	
//	
//	
//		//2.Edit address
//	
//	    //3.Get Address
//	
//}
