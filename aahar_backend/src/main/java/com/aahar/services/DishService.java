package com.aahar.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aahar.dto.DishUpdateDTO;
import com.aahar.dto.DishUploadDTO;

public interface DishService {

	void saveDish(DishUploadDTO dishDTO, MultipartFile imageFile) throws IOException;

	void updateDish(DishUpdateDTO dishDTO, MultipartFile imageFile) throws IOException;

	void deleteDish(Long restaurantId, Long dishId);

}
