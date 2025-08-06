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
      console.log(reponse);
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

          <form onSubmit={handleSubmit} className="space-y-8">
            {/* Section: Basic Info */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                🏪 Basic Information
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Restaurant Name
                  </label>
                  <input
                    type="text"
                    name="restaurantName"
                    value={formData.restaurantName}
                    onChange={handleChange}
                    placeholder="e.g. The Spice House"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Description
                  </label>
                  <input
                    type="text"
                    name="restaurantDescription"
                    value={formData.restaurantDescription}
                    onChange={handleChange}
                    placeholder="Short description"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Phone Number
                  </label>
                  <input
                    type="text"
                    name="phoneNo"
                    value={formData.phoneNo}
                    onChange={handleChange}
                    placeholder="e.g. 9876543210"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Average Cost (₹)
                  </label>
                  <input
                    type="number"
                    name="avgCost"
                    value={formData.avgCost}
                    onChange={handleChange}
                    placeholder="e.g. 500"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
              </div>
            </div>

            {/* Section: Features */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                ✨ Features
              </h3>
              <div className="flex gap-6">
                <label className="flex items-center space-x-2">
                  <input
                    type="checkbox"
                    name="veg"
                    checked={formData.veg}
                    onChange={handleChange}
                    className="accent-green-600"
                  />
                  <span className="text-gray-700">Pure Veg</span>
                </label>
                <label className="flex items-center space-x-2">
                  <input
                    type="checkbox"
                    name="online"
                    checked={formData.online}
                    onChange={handleChange}
                    className="accent-blue-600"
                  />
                  <span className="text-gray-700">Online Available</span>
                </label>
              </div>
            </div>

            {/* Section: Address */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                📍 Address Details
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Address Line 1
                  </label>
                  <input
                    type="text"
                    name="address1"
                    value={formData.address1}
                    onChange={handleChange}
                    placeholder="Street, Building No."
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Address Line 2
                  </label>
                  <input
                    type="text"
                    name="address2"
                    value={formData.address2}
                    onChange={handleChange}
                    placeholder="Landmark, Area"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Address Line 3
                  </label>
                  <input
                    type="text"
                    name="address3"
                    value={formData.address3}
                    onChange={handleChange}
                    placeholder="Optional"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    City
                  </label>
                  <input
                    type="text"
                    name="city"
                    value={formData.city}
                    onChange={handleChange}
                    placeholder="e.g. Mumbai"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    State
                  </label>
                  <input
                    type="text"
                    name="state"
                    value={formData.state}
                    onChange={handleChange}
                    placeholder="e.g. Maharashtra"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Pin Code
                  </label>
                  <input
                    type="text"
                    name="pinCode"
                    value={formData.pinCode}
                    onChange={handleChange}
                    placeholder="e.g. 400001"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
              </div>
            </div>

            {/* Section: Location */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                📌 Map Coordinates
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Latitude
                  </label>
                  <input
                    type="number"
                    name="latitude"
                    value={formData.latitude}
                    onChange={handleChange}
                    placeholder="e.g. 19.0760"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="block text-sm text-gray-600 mb-1">
                    Longitude
                  </label>
                  <input
                    type="number"
                    name="longitude"
                    value={formData.longitude}
                    onChange={handleChange}
                    placeholder="e.g. 72.8777"
                    className="w-full border p-3 rounded-lg shadow-sm focus:ring-2 focus:ring-orange-500"
                  />
                </div>
              </div>
            </div>

            {/* Submit */}
            <div className="text-right pt-4">
              <button
                type="submit"
                className="bg-orange-600 hover:bg-orange-700 text-white py-3 px-8 rounded-full shadow-lg transition duration-300"
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
