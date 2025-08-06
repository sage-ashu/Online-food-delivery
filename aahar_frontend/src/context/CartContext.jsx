import { createContext, useContext, useEffect, useState } from "react";
import {
  fetchCart,
  syncCart,
  addItemToCartBackend,
  removeItemFromCartBackend,
  clearCartBackend,
} from "../services/cartService";
import { toast } from "react-toastify";

const CartContext = createContext();

const CART_STORAGE_KEY = "cartItems";
const RESTAURANT_ID_KEY = "restaurantId";

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);
  const [restaurantId, setRestaurantId] = useState(null);

  // ✅ Get authUser from localStorage
  const getAuthUser = () => {
    try {
      return JSON.parse(localStorage.getItem("authUser"));
    } catch {
      return null;
    }
  };

  useEffect(() => {
    const storedCart = localStorage.getItem(CART_STORAGE_KEY);
    const storedRestaurant = localStorage.getItem(RESTAURANT_ID_KEY);

    if (storedCart) setCartItems(JSON.parse(storedCart));
    if (storedRestaurant) setRestaurantId(storedRestaurant);
  }, []);

  useEffect(() => {
    localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(cartItems));
    if (restaurantId) {
      localStorage.setItem(RESTAURANT_ID_KEY, restaurantId);
    } else {
      localStorage.removeItem(RESTAURANT_ID_KEY);
    }
  }, [cartItems, restaurantId]);

  useEffect(() => {
    const sync = async () => {
      const authUser = getAuthUser();
      if (authUser?.id && authUser?.role === "customer") {
        const localCartDTO = {
          customerId: authUser.id,
          restaurantId,
          items: cartItems.map((item) => ({
            dishId: item.id,
            quantity: item.quantity,
          })),
        };
        await syncCart(authUser.id, localCartDTO);
      }
    };
    sync();
  }, [cartItems, restaurantId]);

  const addToCart = async (item, quantity = 1) => {
    if (!item.restaurantId) {
      toast.error("Invalid item data.");
      return;
    }

    if (restaurantId && restaurantId !== item.restaurantId) {
      toast.error("You can only add items from one restaurant at a time!");
      return;
    }

    const existingIndex = cartItems.findIndex((i) => i.id === item.id);
    let updatedCart = [];

    if (existingIndex !== -1) {
      updatedCart = [...cartItems];
      updatedCart[existingIndex].quantity += quantity;
    } else {
      updatedCart = [...cartItems, { ...item, quantity }];
    }

    setCartItems(updatedCart);
    setRestaurantId(item.restaurantId);

    const authUser = getAuthUser();
    if (authUser?.id && authUser.role === "customer") {
      await addItemToCartBackend(authUser.id, item.id, quantity);
    }

    toast.success(`Added ${item.dishName} x${quantity} to cart`);
  };

  const removeFromCart = async (id) => {
    const updatedCart = cartItems.filter((item) => item.id !== id);
    setCartItems(updatedCart);

    if (updatedCart.length === 0) {
      setRestaurantId(null);
    }

    const authUser = getAuthUser();
    if (authUser?.id && authUser.role === "customer") {
      await removeItemFromCartBackend(authUser.id, id);
    }
  };

  const clearCart = async () => {
    setCartItems([]);
    setRestaurantId(null);

    const authUser = getAuthUser();
    if (authUser?.id && authUser.role === "customer") {
      await clearCartBackend(authUser.id);
    }
  };

  return (
    <CartContext.Provider
      value={{
        cartItems,
        addToCart,
        removeFromCart,
        clearCart,
        restaurantId,
        setCartItems,
        setRestaurantId,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export const useCart = () => useContext(CartContext);
