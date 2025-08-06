package com.aahar.servicesImplementaion;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aahar.dao.*;
import com.aahar.dto.*;
import com.aahar.entities.*;
import com.aahar.services.CartService;
import com.aahar.custom_exception.*;

@Service
@Transactional
public class CartServiceImplementation implements CartService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;
    
    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public ApiResponse getCartByCustomerId(Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Cart cart = cartDao.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartDao.save(newCart);
                });

        return new ApiResponse(true, "Cart fetched", cart);
    }

    @Override
    public ApiResponse addItemToCart(Long customerId, CartItemDTO itemDTO) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Dish dish = dishDao.findById(itemDTO.getDishId())
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));

        Cart cart = cartDao.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    newCart.setRestaurant(dish.getMyRestaurant());
                    return cartDao.save(newCart);
                });

        // Check if cart is for the same restaurant
        if (!cart.getRestaurant().getId().equals(dish.getMyRestaurant().getId())) {
            cart.clearItems();
            cart.setRestaurant(dish.getMyRestaurant());
        }

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(item -> item.getDish().getId().equals(itemDTO.getDishId()))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + itemDTO.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setDish(dish);
            newItem.setQuantity(itemDTO.getQuantity());
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        cartDao.save(cart);
        return new ApiResponse(true, "Item added to cart");
    }

    @Override
    public ApiResponse removeItemFromCart(Long customerId, Long dishId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Cart cart = cartDao.findByCustomer(customer)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getDish().getId().equals(dishId));
        return new ApiResponse(true, "Item removed from cart");
    }

    @Override
    public ApiResponse clearCart(Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Cart cart = cartDao.findByCustomer(customer)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.clearItems();
        return new ApiResponse(true, "Cart cleared");
    }

    @Override
    public ApiResponse syncCart(Long customerId, CartDTO localCart) {
    	System.out.println(localCart.toString());
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Cart cart = cartDao.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartDao.save(newCart);
                });

        
        cart.clearItems();
        localCart.setRestaurantId(1l);
        if (localCart.getRestaurantId() == null) {
            throw new IllegalArgumentException("Restaurant ID must not be null.");
        }

        // ✅ Fetch restaurant from DB instead of creating transient one
        Restaurant restaurant = restaurantDao.findById(localCart.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        cart.setRestaurant(restaurant);

        for (CartItemDTO itemDTO : localCart.getItems()) {
            Dish dish = dishDao.findById(itemDTO.getDishId())
                    .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
            CartItem item = new CartItem();
            item.setDish(dish);
            item.setQuantity(itemDTO.getQuantity());
            item.setCart(cart);
            cart.addItem(item);
        }

        cartDao.save(cart);
        return new ApiResponse(true, "Cart synced successfully");
    }

}
