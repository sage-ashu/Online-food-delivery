// src/services/registrationService.js
// import axios from "axios";
import axiosInstance from "./axiosInstance";

const OWNER_API_BASE_URL = "http://localhost:9090/api/owner";
const CUSTOMER_API_BASE_URL = "http://localhost:9090/customer";

// Register restaurant (already implemented)
export const registerRestaurant = async (restaurantData) => {
  try {
    const response = await axiosInstance.post(`${OWNER_API_BASE_URL}/register`, restaurantData);
    return response.data;
  } catch (error) {
    if (error.response) {
      return { success: false, message: error.response.data.message || "Registration failed" };
    }
    return { success: false, message: "Network error" };
  }
};

// ✅ Register customer (new function)
export const registerCustomer = async (customerData) => {
  try {
    const response = await axiosInstance.post(`${CUSTOMER_API_BASE_URL}/register`, customerData);
    return response.data;
  } catch (error) {
    if (error.response) {
      return { success: false, message: error.response.data.message || "Registration failed" };
    }
    return { success: false, message: "Network error" };
  }
};
