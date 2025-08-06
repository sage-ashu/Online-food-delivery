import axios from "axios";

const BASE_URL = "http://localhost:9090/api/owner/restaurant";

export const getRestaurantByOwner = async (ownerId) => {
  const response = await axios.get(`${BASE_URL}/${ownerId}`);
  console.log(response)
  return response.data;
};

export const saveOrUpdateRestaurant = async (restaurantData) => {
  const response = await axios.post(`http://localhost:9090/api/owner/restaurant/saveOrUpdate`, restaurantData);
  return response.data;
};

