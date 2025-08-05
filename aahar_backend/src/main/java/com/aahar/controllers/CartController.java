package com.aahar.controllers;

import com.aahar.dto.*;
import com.aahar.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PostMapping("/add/{customerId}")
    public ResponseEntity<?> addToCart(@PathVariable Long customerId,
                                       @RequestBody CartItemDTO itemDTO) {
        return ResponseEntity.ok(cartService.addItemToCart(customerId, itemDTO));
    }

    @DeleteMapping("/remove/{customerId}/{dishId}")
    public ResponseEntity<?> removeItem(@PathVariable Long customerId,
                                        @PathVariable Long dishId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(customerId, dishId));
    }

    @DeleteMapping("/clear/{customerId}")
    public ResponseEntity<?> clearCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.clearCart(customerId));
    }

    @PostMapping("/sync/{customerId}")
    public ResponseEntity<?> syncCart(@PathVariable Long customerId,
                                      @RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.syncCart(customerId, cartDTO));
    }
}
