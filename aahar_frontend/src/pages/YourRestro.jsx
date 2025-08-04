import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { useAuth } from "../context/AuthContext";
import {
  getRestaurantByOwner,
  saveOrUpdateRestaurant,
} from "../services/restaurantService";
import { toast } from "react-toastify";

const initialForm = {
  restaurantName: "",
  restaurantDescription: "",
  veg: false, // ✅ updated
  avgCost: 0,
  online: false, // ✅ updated
  phoneNo: "",
  address1: "",
  address2: "",
  address3: "",
  city: "",
  state: "",
  pinCode: "",
  latitude: 0,
  longitude: 0,
};

function YourRestro() {
  const { authUser } = useAuth();
  const [formData, setFormData] = useState(initialForm);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getRestaurantByOwner(authUser.id);

        if (data?.data) {
          setFormData((prev) => ({ ...prev, ...data.data }));
        }
      } catch (err) {
        console.log("No existing restaurant info found");
      }
    };

    if (authUser?.id) fetchData();
  }, [authUser?.id]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = { ...formData, ownerId: authUser.id };
        console.log(payload);
      const reponse = await saveOrUpdateRestaurant(payload);
      console.log(reponse)
      toast.success("Restaurant information saved/updated successfully.");
    } catch (err) {
      toast.error("Failed to save/update restaurant information.");
      console.error(err);
    }
  };

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />

      <main className="flex-grow bg-gradient-to-br from-orange-50 to-orange-100 px-4 py-10">
        <div className="max-w-4xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
          <h2 className="text-3xl font-bold text-orange-700 text-center mb-8">
            Restaurant Information
          </h2>

          <form onSubmit={handleSubmit} className="grid grid-cols-2 gap-4">
            <input
              type="text"
              name="restaurantName"
              value={formData.restaurantName}
              onChange={handleChange}
              placeholder="Restaurant Name"
              className="border p-2 rounded"
              />
            <input
              type="text"
              name="restaurantDescription"
              value={formData.restaurantDescription} // ✅ fixed
              onChange={handleChange}
              className="border p-2 rounded"
            />

            <div className="flex items-center space-x-2">
              <input
                type="checkbox"
                name="veg"
                checked={formData.veg}
                onChange={handleChange}
              />
              <label>Pure Veg</label>

              <input
                type="checkbox"
                name="online"
                checked={formData.online}
                onChange={handleChange}
              />
              <label>Online Available</label>
            </div>

            <input
              type="number"
              name="avgCost"
              value={formData.avgCost}
              onChange={handleChange}
              placeholder="Average Cost"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="phoneNo"
              value={formData.phoneNo}
              onChange={handleChange}
              placeholder="Phone Number"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="address1"
              value={formData.address1}
              onChange={handleChange}
              placeholder="Address Line 1"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="address2"
              value={formData.address2}
              onChange={handleChange}
              placeholder="Address Line 2"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="address3"
              value={formData.address3}
              onChange={handleChange}
              placeholder="Address Line 3"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="city"
              value={formData.city}
              onChange={handleChange}
              placeholder="City"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="state"
              value={formData.state}
              onChange={handleChange}
              placeholder="State"
              className="border p-2 rounded"
            />
            <input
              type="text"
              name="pinCode"
              value={formData.pinCode}
              onChange={handleChange}
              placeholder="Pin Code"
              className="border p-2 rounded"
            />
            <input
              type="number"
              name="latitude"
              value={formData.latitude}
              onChange={handleChange}
              placeholder="Latitude"
              className="border p-2 rounded"
            />
            <input
              type="number"
              name="longitude"
              value={formData.longitude}
              onChange={handleChange}
              placeholder="Longitude"
              className="border p-2 rounded"
            />

            <div className="col-span-2 text-right">
              <button
                type="submit"
                className="bg-orange-700 text-white py-2 px-6 rounded hover:bg-orange-800 transition duration-300"
              >
                Save Info
              </button>
            </div>
          </form>
        </div>
      </main>
    </div>
  );
}

export default YourRestro;
