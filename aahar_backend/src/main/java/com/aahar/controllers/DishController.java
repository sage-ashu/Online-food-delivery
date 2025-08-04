package com.aahar.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.aahar.dto.DishResponseDTO;
import com.aahar.dto.DishUpdateDTO;
import com.aahar.dto.DishUploadDTO;
import com.aahar.services.DishService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/dish")
@AllArgsConstructor
public class DishController {
	
	private DishService dishService;
//	//1. Add dish by restaurant id
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,path = "/add-dish")
//	public ResponseEntity<?> addDish(@ModelAttribute DishUploadDTO dishDTO, @RequestParam(value="image",required=false) MultipartFile imageFile){
//    	System.out.println(dishDTO.toString());
//    	System.out.println(imageFile.isEmpty());
//    	try {
//			dishService.saveDish(dishDTO,imageFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	return ResponseEntity.ok("Dish saved");
//    }
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/add-dish")
    public ResponseEntity<?> addDish(
        @ModelAttribute DishUploadDTO dishDTO,
        @RequestParam(value = "image", required = false) MultipartFile imageFile,
        @RequestParam("isVeg") boolean isVeg,
        @RequestParam("isAvailable") boolean isAvailable
    ) {
        dishDTO.setVeg(isVeg);
        dishDTO.setAvailable(isAvailable);

        System.out.println(dishDTO);
        try {
            dishService.saveDish(dishDTO, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Dish saved");
    }

	
	//2. Edit dish by restaurant id and dish id
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editDish(@ModelAttribute DishUpdateDTO dishDTO,@RequestParam(value="image",required=false) MultipartFile imageFile){
    	try {
			dishService.updateDish(dishDTO,imageFile);
			return ResponseEntity.ok("Dish updated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating dish");
		}
    }
    
    //3. Delete dish by restaurant id and dish id
    @DeleteMapping("/{restaurantId}/{dishId}")
    public ResponseEntity<?> deleteDish(@PathVariable Long restaurantId,@PathVariable Long dishId){
    	dishService.deleteDish(restaurantId,dishId);
    	return ResponseEntity.ok("Dish deleted");
    }
    
	//4. get dish by dish id
//	@GetMapping("/{dishId}")
//	public ResponseEntity<?> getDish(@PathVariable Long dishId){
//		return ResponseEntity.ok(dishService.getDish(dishId));
//		
//	}
	
	//5. get list of dishes by restaurant id
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getDishListOfRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(dishService.getDishByRestaurant(restaurantId));
    }

	@GetMapping("/get-all-dishes")
	public ResponseEntity<?> getAllDishesForHomePage(){
		return ResponseEntity.status(HttpStatus.OK).body(dishService.getAllDishesForHomePage());
	}
}
