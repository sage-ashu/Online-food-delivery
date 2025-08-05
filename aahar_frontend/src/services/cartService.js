// src/services/cartService.js
import axios from "axios";

const API_BASE = "http://localhost:9090/api/cart";

export const fetchCart = async (customerId) => {
  try {
    const res = await axios.get(`${API_BASE}/${customerId}`);
    return res.data;
  } catch (err) {
    console.error("Error fetching cart:", err);
    return null;
  }
};

export const syncCart = async (customerId, localCart) => {
  try {
    const res = await axios.post(`${API_BASE}/sync/${customerId}`, localCart);
    return res.data;
  } catch (err) {
    console.error("Error syncing cart:", err);
    return null;
  }
};

export const addItemToCartBackend = async (customerId, dishId, quantity) => {
  try {
    const res = await axios.post(`${API_BASE}/add/${customerId}`, {
      dishId,
      quantity,
    });
    return res.data;
  } catch (err) {
    console.error("Error adding item:", err);
    return null;
  }
};

export const removeItemFromCartBackend = async (customerId, dishId) => {
  try {
    const res = await axios.delete(`${API_BASE}/remove/${customerId}/${dishId}`);
    return res.data;
  } catch (err) {
    console.error("Error removing item:", err);
    return null;
  }
};

export const clearCartBackend = async (customerId) => {
  try {
    const res = await axios.delete(`${API_BASE}/clear/${customerId}`);
    return res.data;
  } catch (err) {
    console.error("Error clearing cart:", err);
    return null;
  }
};
