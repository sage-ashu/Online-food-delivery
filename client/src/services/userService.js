import axios from 'axios';

const API_URL = 'http://localhost:8080/api/user';

export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, userData);
    return response.data;
  } catch (error) {
    throw error.response?.data || { message: "User registration failed" };
  }
};
