import axios from 'axios';

const API_URL = 'http://localhost:9090/api/owner';



///This service is to register restaurant owner 
export const registerRestaurant = async (restaurantData) => {
  try {
    console.log(restaurantData)
    const response = await axios.post(`${API_URL}/register`, restaurantData);
    console.log(response);
    return response.data;
  } catch (error) {
    throw error.response?.data || { message: "Restaurant registration failed" };
  }
};
