package com.aahar.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aahar.dto.DishResponseDTO;
import com.aahar.dto.DishUpdateDTO;
import com.aahar.dto.DishUploadDTO;

public interface DishService {

	void saveDish(DishUploadDTO dishDTO, MultipartFile imageFile) throws IOException;

	void updateDish(DishUpdateDTO dishDTO, MultipartFile imageFile) throws IOException;

	void deleteDish(Long restaurantId, Long dishId);

	Object getDish(Long dishId);

	List<DishResponseDTO> getDishByRestaurant(Long restaurantId);

}
