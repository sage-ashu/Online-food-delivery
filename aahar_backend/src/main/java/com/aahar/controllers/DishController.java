package com.aahar.controllers;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aahar.dto.DishUploadDTO;
import com.aahar.services.DishService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/dish")
@AllArgsConstructor
public class DishController {
	
	private DishService dishService;
	//1. Add dish by restaurant id
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addDish(@ModelAttribute DishUploadDTO dishDTO, @RequestParam("image") MultipartFile imageFile){
    	System.out.println("inside");
    	System.out.println(dishDTO.toString());
    	try {
			dishService.saveDish(dishDTO,imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.ok("Dish saved");
    }
	
	//2. Edit dish by restaurant id
	//3. Delete dish by restaurant id
	//4. get list of dish by restaurant id
	
}
