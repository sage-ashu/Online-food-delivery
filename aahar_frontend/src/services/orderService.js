// import axios from "axios";
import axiosInstance from "./axiosInstance";

const BASE_URL = "http://localhost:9090";

export const createOrder = async (orderData) => {
  const response = await axiosInstance.post(`${BASE_URL}/order/add`, orderData);
  return response.data;
};

export const getOrdersByCustomer = async (customerId) => {
  const response = await axiosInstance.get(`${BASE_URL}/order/customer/${customerId}`);
  return response.data;
};

export const getOrdersByRestaurant = async (restaurantId) => {
  const response = await axiosInstance.get(`${BASE_URL}/order/restaurant/${restaurantId}`);
  return response.data;
};

export const updateOrderStatus = async (orderId, status) => {
  const response = await axiosInstance.put(`${BASE_URL}/order/${orderId}/status`, { status });
  return response.data;
};
