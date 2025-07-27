package com.aahar.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aahar.entities.RestaurantAddress;
import com.aahar.dto.RestaurantAddressDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Map Restaurant -> restaurantId inside DTO
        mapper.typeMap(RestaurantAddress.class, RestaurantAddressDTO.class)
              .addMappings(m -> m.map(
                      src -> (src.getRestaurant() != null ? src.getRestaurant().getId() : null),
                      RestaurantAddressDTO::setRestaurantId
              ));

        return mapper;
    }
}
