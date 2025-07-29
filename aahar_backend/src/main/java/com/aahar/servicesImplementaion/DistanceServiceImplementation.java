package com.aahar.servicesImplementaion;

//public class DistanceService {
//
//}
////service/DistanceService.java
//package com.aahar.service;
//
//import com.aahar.dto.DistanceMatrixResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aahar.dto.DistanceMatrixResponseDTO;
import com.aahar.services.DistanceService;

@Service
@RequiredArgsConstructor
public class DistanceServiceImplementation implements DistanceService {

 @Value("${google.api.key}")
 private String apiKey;

 private final WebClient webClient = WebClient.create("https://maps.googleapis.com");

 public int getDistanceInMeters(double originLat, double originLng, double destLat, double destLng) {
     String origins = originLat + "," + originLng;
     String destinations = destLat + "," + destLng;

     DistanceMatrixResponseDTO response = webClient.get()
             .uri(uriBuilder -> uriBuilder
                     .path("/maps/api/distancematrix/json")
                     .queryParam("origins", origins)
                     .queryParam("destinations", destinations)
                     .queryParam("key", apiKey)
                     .build())
             .retrieve()
             .bodyToMono(DistanceMatrixResponseDTO.class)
             .block(); // block is okay here for synchronous service

     if (response == null || response.getRows().isEmpty() ||
             response.getRows().get(0).getElements().isEmpty()) {
         throw new RuntimeException("Distance Matrix API returned invalid response");
     }

     return response.getRows().get(0).getElements().get(0).getDistance().getValue(); // in meters
 }
}

