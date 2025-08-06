package com.aahar.controllers;

import com.aahar.config.JwtUtil;
import com.aahar.dto.*;
import com.aahar.services.CartService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
	private final JwtUtil jwtUtil;
    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<?> getCart(HttpServletRequest request) {
    	String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(HttpServletRequest request,@RequestBody CartItemDTO itemDTO) {
    	String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);
        return ResponseEntity.ok(cartService.addItemToCart(customerId, itemDTO));
    }

    @DeleteMapping("/remove/{dishId}")
    public ResponseEntity<?> removeItem(HttpServletRequest request,
                                        @PathVariable Long dishId) {
    	String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);
        return ResponseEntity.ok(cartService.removeItemFromCart(customerId, dishId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(HttpServletRequest request) {
    	String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);
        return ResponseEntity.ok(cartService.clearCart(customerId));
    }

    @PostMapping("/sync")
    public ResponseEntity<?> syncCart(HttpServletRequest request,
                                      @RequestBody CartDTO cartDTO) {
    	String token = jwtUtil.extractTokenFromRequest(request);
		 Long customerId = jwtUtil.extractId(token);
        return ResponseEntity.ok(cartService.syncCart(customerId, cartDTO));
    }
}
