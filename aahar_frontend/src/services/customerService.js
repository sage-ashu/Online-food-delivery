// import axios from "axios";
import axiosInstance from "./axiosInstance";


const BASE_URL = "http://localhost:9090/api/Customer-address";

export const getCustomerAddressById = (customerId) => {
  return axiosInstance.get(`${BASE_URL}/customer/${customerId}`);
};

export const addCustomerAddress = (addressData) => {
  return axiosInstance.post(BASE_URL, addressData);
};

export const editCustomerAddress = (id, addressData) => {
  return axiosInstance.put(`${BASE_URL}/${id}`, addressData);
};
