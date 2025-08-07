// src/services/loginService.js
// import axios from "axios";
import axiosInstance from "./axiosInstance";

const RESTAURANT_API_URL = "http://localhost:9090/api/owner";
const CUSTOMER_API_URL = "http://localhost:9090/customer";

export const loginRestaurantOwner = async (email, password) => {
  try {
    const response = await axiosInstance.post(`${RESTAURANT_API_URL}/login`, {
      email,
      password,
    });
    return response.data; // { success, message, data }
  } catch (error) {
    if (error.response) {
      return error.response.data;
    } else {
      return { success: false, message: "Server not reachable" };
    }
  }
};

export const loginCustomer = async (email, password) => {
  try {
    const response = await axiosInstance.post(`${CUSTOMER_API_URL}/login`, {
      email,
      password,
    });
    return response.data; // { success, message, data }
  } catch (error) {
    if (error.response) {
      return error.response.data;
    } else {
      return { success: false, message: "Server not reachable" };
    }
  }
};
