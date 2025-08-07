// import axios from "axios";
import axiosInstance from "./axiosInstance";

const API_BASE = "http://localhost:9090/dish"; // Use your backend base URL

// ✅ Get all dishes
export const getAllDishes = async () => {
  try {
    const res = await axiosInstance.get(`${API_BASE}/get-all-dishes`);
    // console.log(res);
    return res.data; // assuming { success, message, data }
  } catch (error) {
    console.error("Error fetching dishes:", error);
    throw error;
  }
};

// ✅ Add a new dish
export const addDish = async (restaurantId, formData) => {
  const data = new FormData();
  data.append("restaurantId", restaurantId);
  data.append("dishName", formData.dishName);
  data.append("dishPrice", formData.dishPrice);
  data.append("description", formData.description);
  data.append("isVeg", formData.isVeg);
  data.append("preperationTime", formData.preperationTime);
  data.append("noOfServings", formData.noOfServings);
  data.append("isAvailable", formData.isAvailable);

  if (formData.image) {
    data.append("image", formData.image);
  }
  console.log(formData);
  try {
    const response = await axiosInstance.post(`${API_BASE}/add-dish`, data, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error adding dish:", error);
    throw error;
  }
};

// ✅ Get dishes for a restaurant
export const getDishesByRestaurant = async (restaurantId) => {
  try {
    const res = await axiosInstance.get(`${API_BASE}/${restaurantId}`);
    return res.data; // returns List<DishResponseDTO>
  } catch (err) {
    console.error("Failed to get dishes for restaurant", err);
    throw err;
  }
};

// ✅ Delete dish
export const deleteDishById = async (restaurantId, dishId) => {
  console.log(restaurantId,dishId)
  try {
    await axiosInstance.delete(`${API_BASE}/${restaurantId}/${dishId}`);
  } catch (err) {
    console.error("Failed to delete dish", err);
    throw err;
  }
};
