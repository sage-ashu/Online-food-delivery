// services/restaurantOwnerService.js
import axios from 'axios';

const API_URL = 'http://localhost:9090/api/owner';

// Restaurant owner registration
export const registerRestaurant = async (restaurantData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, restaurantData);
    return response.data;
  } catch (error) {
    throw error.response?.data || { message: "Restaurant registration failed" };
  }
};

// 🔐 Restaurant owner login
export const loginRestaurantOwner = async (email, password) => {
  try {
    console.log(email,password)
    const response = await axios.post(`${API_URL}/login`, { email, password });
    
    return response.data; // Expecting name, email, role from backend
  } catch (error) {
    throw error.response?.data || { message: "Login failed" };
  }
};
