package com.aahar.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aahar.dto.DishUploadDTO;

public interface DishService {

	void saveDish(DishUploadDTO dishDTO, MultipartFile imageFile) throws IOException;

}
