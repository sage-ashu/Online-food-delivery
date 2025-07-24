import axios from 'axios';

const API_URL = 'http://localhost:8080/api/restaurant';

export const registerRestaurant = async (restaurantData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, restaurantData);
    return response.data;
  } catch (error) {
    throw error.response?.data || { message: "Restaurant registration failed" };
  }
};
