// src/services/loginService.js
import axios from "axios";

const API_BASE_URL = "http://localhost:9090/api/owner"; // Adjust base URL as needed

export const loginRestaurantOwner = async (email, password) => {
    console.log(email,password);
  try {
    const response = await axios.post(`${API_BASE_URL}/login`, {
      email,
      password,
    });
    console.log(response)
    return response.data; // { success, message, data }
  } catch (error) {
    if (error.response) {
      return error.response.data; // Error from backend
    } else {
      return { success: false, message: "Server not reachable" };
    }
  }
};
