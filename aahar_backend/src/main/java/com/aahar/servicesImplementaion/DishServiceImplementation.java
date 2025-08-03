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
import com.aahar.dao.DishDao;
import com.aahar.dao.RestaurantDao;
import com.aahar.dto.DishUpdateDTO;
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
	private DishDao dishDao;
	
	@Override
	public void saveDish(DishUploadDTO dishDTO, MultipartFile imageFile) throws IOException {
		
			Restaurant restaurantEntity =  restaurantDao.findById(dishDTO.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
			System.out.println(restaurantEntity.toString());
			Dish dish = new Dish();
			restaurantEntity.addDish(dish);
			dish.setMyRestaurant(restaurantEntity);
			modelMapper.map(dishDTO, dish);
			System.out.println(dish.toString());
			if(imageFile!=null) {
				System.out.println("image");
				File dir = new File("uploads/dishes/");
				if(!dir.exists()) {
					dir.mkdirs();
				}
				
				String fileName = UUID.randomUUID()+"_"+imageFile.getOriginalFilename();
				Path filePath = Paths.get("uploads/dishes/", fileName);
				Files.write(filePath, imageFile.getBytes());
				
				dish.setImagePath(filePath.toString());
			}
			else {
				System.out.println("noimage");
				dish.setImagePath(null);
			}
			System.out.println(dish.toString());
			
			
}

	@Override
	public void updateDish(DishUpdateDTO dishDTO, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		Dish dishEntity = dishDao.findById(dishDTO.getDishId()).orElseThrow(()->new ResourceNotFoundException("invalid dish id"));
		if(dishEntity.getMyRestaurant().getId()!=dishDTO.getRestaurantId()) {
			throw  new ResourceNotFoundException("Dish does not belong to this restaurant");
		}
		if (dishEntity.getImagePath() != null) {
            File oldImage = new File(dishEntity.getImagePath());
            if (oldImage.exists()) {
                oldImage.delete();
            }
		}
		modelMapper.map(dishDTO, dishEntity);
		if(imageFile!=null) {
			System.out.println("image");
			File dir = new File("uploads/dishes/");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String fileName = UUID.randomUUID()+"_"+imageFile.getOriginalFilename();
			Path filePath = Paths.get("uploads/dishes/", fileName);
			Files.write(filePath, imageFile.getBytes());
			
			dishEntity.setImagePath(filePath.toString());
		}
		else {
			System.out.println("no file");
			dishEntity.setImagePath(null);
		}
		

	}

	@Override
	public void deleteDish(Long restaurantId, Long dishId) {
		// TODO Auto-generated method stub
		Dish dishEntity = dishDao.findById(dishId).orElseThrow(()->new ResourceNotFoundException("invalid dish id"));
		if(dishEntity.getMyRestaurant().getId()!=restaurantId) {
			throw  new ResourceNotFoundException("Dish does not belong to this restaurant");
		}
		if (dishEntity.getImagePath() != null) {
            File oldImage = new File(dishEntity.getImagePath());
            if (oldImage.exists()) {
                oldImage.delete();
            }
		}
		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(()->new ResourceNotFoundException("Restaurant not found"));
		restaurant.removeDish(dishEntity);
	}
}

