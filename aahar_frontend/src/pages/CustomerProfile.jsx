import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { useAuth } from "../context/AuthContext";
import { toast } from "react-toastify";
import {
  getCustomerAddressById,
  addCustomerAddress,
  editCustomerAddress,
} from "../services/customerService";

const initialForm = {
  phoneNo: "",
  address1: "",
  address2: "",
  address3: "",
  city: "",
  pinCode: "",
  latitude: 0.0,
  longitude: 0.0,
};

function CustomerProfile() {
  const { authUser } = useAuth();
  const [formData, setFormData] = useState(initialForm);
  const [addressId, setAddressId] = useState(null); // For editing if already exists

  useEffect(() => {
    const fetchAddress = async () => {
      try {
        const res = await getCustomerAddressById(authUser.id);
        if (res.data.length > 0) {
          const address = res.data[0]; // Assuming one address per customer
          setFormData(address);
          setAddressId(address.id); // Save for PUT
        }
      } catch (err) {
        console.log("No address found.");
      }
    };

    if (authUser?.id) fetchAddress();
  }, [authUser?.id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = { ...formData, customerId: authUser.id };

      if (addressId) {
        await editCustomerAddress(addressId, payload);
        toast.success("Address updated successfully.");
      } else {
        await addCustomerAddress(payload);
        toast.success("Address added successfully.");
      }
    } catch (err) {
      toast.error("Error saving address.");
      console.error(err);
    }
  };

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <main className="flex-grow bg-gradient-to-br from-orange-50 to-orange-100 px-4 py-10">
        <div className="max-w-4xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
          <h2 className="text-3xl font-bold text-orange-700 text-center mb-8">
            Customer Address Profile
          </h2>

          <form onSubmit={handleSubmit} className="space-y-6">
            {/* Contact Info */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                📞 Contact Info
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Phone Number
                  </label>
                  <input
                    type="text"
                    name="phoneNo"
                    value={formData.phoneNo}
                    onChange={handleChange}
                    placeholder="Enter phone number"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
              </div>
            </div>

            {/* Address Info */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                🏠 Address
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Address Line 1
                  </label>
                  <input
                    type="text"
                    name="address1"
                    value={formData.address1}
                    onChange={handleChange}
                    placeholder="Street, Building No."
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Address Line 2
                  </label>
                  <input
                    type="text"
                    name="address2"
                    value={formData.address2}
                    onChange={handleChange}
                    placeholder="Landmark, Area"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Address Line 3
                  </label>
                  <input
                    type="text"
                    name="address3"
                    value={formData.address3}
                    onChange={handleChange}
                    placeholder="Optional"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    City
                  </label>
                  <input
                    type="text"
                    name="city"
                    value={formData.city}
                    onChange={handleChange}
                    placeholder="City name"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Pin Code
                  </label>
                  <input
                    type="text"
                    name="pinCode"
                    value={formData.pinCode}
                    onChange={handleChange}
                    placeholder="e.g. 560001"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
              </div>
            </div>

            {/* Location Info */}
            <div>
              <h3 className="text-xl font-semibold text-gray-700 mb-4">
                📍 Location Coordinates
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Latitude
                  </label>
                  <input
                    type="number"
                    step="any"
                    name="latitude"
                    value={formData.latitude}
                    onChange={handleChange}
                    placeholder="Enter latitude"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
                <div>
                  <label className="text-sm text-gray-600 mb-1 block">
                    Longitude
                  </label>
                  <input
                    type="number"
                    step="any"
                    name="longitude"
                    value={formData.longitude}
                    onChange={handleChange}
                    placeholder="Enter longitude"
                    className="w-full border p-3 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500"
                  />
                </div>
              </div>
            </div>

            {/* Submit Button */}
            <div className="text-right mt-6">
              <button
                type="submit"
                className="bg-orange-600 hover:bg-orange-700 text-white px-6 py-3 rounded-full shadow-lg transition duration-300"
              >
                {addressId ? "Update Address" : "Save Address"}
              </button>
            </div>
          </form>
        </div>
      </main>
    </div>
  );
}

export default CustomerProfile;
