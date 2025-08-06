package com.aahar.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aahar.dto.AddOrderDTO;
import com.aahar.dto.CustomerOrderResponseDTO;
import com.aahar.dto.RatingDTO;
import com.aahar.dto.RestaurantOrderResponseDTO;
import com.aahar.dto.UpdateOrderStatusDTO;
import com.aahar.services.OrdersService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    // 1. Add Order
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderDTO orderDTO) {
        ordersService.addOrder(orderDTO);
        return ResponseEntity.ok("Order placed successfully");
    }

    // 2. Get orders by Customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomer(@PathVariable Long customerId) {
        List<CustomerOrderResponseDTO> orders = ordersService.getCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }

    // 3. Get orders by Restaurant ID
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        List<RestaurantOrderResponseDTO> orders = ordersService.getRestaurantOrders(restaurantId);
        return ResponseEntity.ok(orders);
    }

    // 4. Update Order Status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId,
                                               @RequestBody UpdateOrderStatusDTO dto) {
        ordersService.updateOrderStatus(orderId, dto.getStatus());
        return ResponseEntity.ok("Order status updated successfully.");
    }
    
    @PutMapping("/rating")
    public ResponseEntity<?> rating(@RequestBody RatingDTO ratingDTO){
    	ordersService.updateRating(ratingDTO);
    	return ResponseEntity.ok("rating updated");
    }
}
