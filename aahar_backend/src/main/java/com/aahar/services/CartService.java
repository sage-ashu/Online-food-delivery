package com.aahar.services;

import com.aahar.dto.CartDTO;
import com.aahar.dto.CartItemDTO;
import com.aahar.dto.ApiResponse;

public interface CartService {
    ApiResponse getCartByCustomerId(Long customerId);
    ApiResponse addItemToCart(Long customerId, CartItemDTO itemDTO);
    ApiResponse removeItemFromCart(Long customerId, Long dishId);
    ApiResponse clearCart(Long customerId);
    ApiResponse syncCart(Long customerId, CartDTO localCart);
}
