import axios from "axios";

const BASE_URL = "http://localhost:9090/api/Customer-address";

export const getCustomerAddressById = (customerId) => {
  return axios.get(`${BASE_URL}/customer/${customerId}`);
};

export const addCustomerAddress = (addressData) => {
  return axios.post(BASE_URL, addressData);
};

export const editCustomerAddress = (id, addressData) => {
  return axios.put(`${BASE_URL}/${id}`, addressData);
};
