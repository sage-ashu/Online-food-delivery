import axios from 'axios';

const API_URL = 'http://localhost:5000/api/delivery';

export const registerDeliveryAgent = async (deliveryData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, deliveryData);
    return response.data;
  } catch (error) {
    throw error.response?.data || { message: "Delivery agent registration failed" };
  }
};
