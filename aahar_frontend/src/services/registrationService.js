// src/services/registrationService.js
import axios from "axios";

const API_BASE_URL = "http://localhost:9090/api/owner";

export const registerRestaurant = async (restaurantData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/register`, restaurantData);
    return response.data;
  } catch (error) {
    if (error.response) {
      return { success: false, message: error.response.data.message || "Registration failed" };
    }
    return { success: false, message: "Network error" };
  }
};
