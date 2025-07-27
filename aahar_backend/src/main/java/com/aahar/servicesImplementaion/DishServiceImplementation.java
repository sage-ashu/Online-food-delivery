package com.aahar.servicesImplementaion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aahar.custom_exception.ResourceNotFoundException;
import com.aahar.dao.RestaurantDao;
import com.aahar.dto.DishUploadDTO;
import com.aahar.entities.Dish;
import com.aahar.entities.Restaurant;
import com.aahar.services.DishService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DishServiceImplementation implements DishService {

	//private String uploadDir = "uploads/dishes/";
	private ModelMapper modelMapper;
	private RestaurantDao restaurantDao;
	
	@Override
	public void saveDish(DishUploadDTO dishDTO, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		
			Restaurant restaurantEntity =  restaurantDao.findById(dishDTO.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
			File dir = new File("uploads/dishes/");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String fileName = UUID.randomUUID()+"_"+imageFile.getOriginalFilename();
			Path filePath = Paths.get("uploads/dishes/", fileName);
			Files.write(filePath, imageFile.getBytes());
			
			Dish dish =new Dish();
			System.out.println(dish.toString());
			dish = modelMapper.map(dishDTO, Dish.class);
			dish.setImagePath(filePath.toString());
			restaurantEntity.addDish(dish);
			
		
		
		
		
		
		
		

	}

}
