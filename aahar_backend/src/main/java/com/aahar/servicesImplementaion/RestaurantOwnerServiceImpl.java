package com.aahar.servicesImplementaion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dto.ApiResponse;
import com.aahar.dto.PasswordUpdateDTO;
import com.aahar.dto.RestaurantOwnerLoginDTO;
import com.aahar.dto.RestaurantOwnerRegistrationDTO;
import com.aahar.dto.RestaurantOwnerResponseDTO;
import com.aahar.dto.RestaurantOwnerUpdateDTO;
import com.aahar.entities.RestaurantOwner;
import com.aahar.services.RestaurantOwnerService;

import lombok.AllArgsConstructor;

import com.aahar.dao.OrdersDao;
import com.aahar.dao.RestaurantOwnerDao;

import java.util.Optional;
@Service
@Transactional
@AllArgsConstructor
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {

    @Autowired
    private RestaurantOwnerDao ownerRepo;

    @Override
    public ApiResponse registerOwner(RestaurantOwnerRegistrationDTO dto) {
        // Check if email already exists (optional enhancement)
        boolean exists = ownerRepo.existsByEmail(dto.getEmail());
        if (exists) {
            return new ApiResponse(false, "Email already registered.");
        }

        RestaurantOwner owner = new RestaurantOwner(
            dto.getName(),
            dto.getPhoneNumber(),
            dto.getEmail(),
            dto.getPassword() // You can hash the password here using BCrypt
        );

        RestaurantOwner saved = ownerRepo.save(owner);

        return new ApiResponse(true, "Owner registered successfully", saved);
    }
    
    
    @Override
    public ApiResponse updateOwnerDetails(Long ownerId, RestaurantOwnerUpdateDTO dto) {
        Optional<RestaurantOwner> optionalOwner = ownerRepo.findById(ownerId);

        if (optionalOwner.isEmpty()) {
            return new ApiResponse(false, "Owner not found with ID: " + ownerId);
        }

        RestaurantOwner owner = optionalOwner.get();

        if (dto.getName() != null) owner.setName(dto.getName());
        if (dto.getPhoneNumber() != null) owner.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail() != null) owner.setEmail(dto.getEmail());
        if (dto.getPassword() != null) owner.setPassword(dto.getPassword());

        RestaurantOwner updated = ownerRepo.save(owner);

        // Convert entity to DTO
        RestaurantOwnerResponseDTO responseDTO = new RestaurantOwnerResponseDTO(
            updated.getId(),
            updated.getName(),
            updated.getEmail(),
            updated.getPhoneNumber()
        );

        return new ApiResponse(true, "Owner details updated successfully", responseDTO);
    }
    
    @Override
    public ApiResponse loginOwner(RestaurantOwnerLoginDTO dto) {
        Optional<RestaurantOwner> optionalOwner = ownerRepo.findByEmail(dto.getEmail());

        if (optionalOwner.isEmpty()) {
            return new ApiResponse(false, "Invalid email or password");
        }

        RestaurantOwner owner = optionalOwner.get();

        // Plain text comparison (for now, secure hashing can be added later)
        if (!owner.getPassword().equals(dto.getPassword())) {
            return new ApiResponse(false, "Invalid email or password");
        }

        RestaurantOwnerResponseDTO responseDTO = new RestaurantOwnerResponseDTO(
            owner.getId(),
            owner.getName(),
            owner.getEmail(),
            owner.getPhoneNumber()
        );

        return new ApiResponse(true, "Login successful", responseDTO);
    }
    
    @Override
    public ApiResponse updateOwnerPassword(Long ownerId, PasswordUpdateDTO dto) {
        Optional<RestaurantOwner> optionalOwner = ownerRepo.findById(ownerId);

        if (optionalOwner.isEmpty()) {
            return new ApiResponse(false, "Owner not found with ID: " + ownerId);
        }

        RestaurantOwner owner = optionalOwner.get();

        owner.setPassword(dto.getNewPassword()); // ⚠️ You can hash this with BCrypt if needed
        ownerRepo.save(owner);

        return new ApiResponse(true, "Password updated successfully");
    }

    
    @Override
    public ApiResponse getOwnerDetails(Long ownerId) {
        Optional<RestaurantOwner> optionalOwner = ownerRepo.findById(ownerId);

        if (optionalOwner.isEmpty()) {
            return new ApiResponse(false, "Owner not found with ID: " + ownerId);
        }

        RestaurantOwner owner = optionalOwner.get();

        // Convert to response DTO (to avoid LazyInitializationException)
        RestaurantOwnerResponseDTO dto = new RestaurantOwnerResponseDTO(
            owner.getId(),
            owner.getName(),
            owner.getEmail(),
            owner.getPhoneNumber()
        );

        return new ApiResponse(true, "Owner found", dto);
    }


    

}
