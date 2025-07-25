import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar"; // adjust if path differs

function R_Restaurant_Account() {
  const navigate = useNavigate();

  // Pre-filled registration details
  const [formData, setFormData] = useState({
    name: "Pizza Palace",
    email: "contact@pizzapalace.com",
    phone: "9876543210",
    address: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Restaurant Account Data:", formData);
  };

  const handleLogout = () => {
    localStorage.removeItem("user"); // Or use your logout logic
    navigate("/login");
  };

  const navLinks = [
    { name: "Home", path: "/restaurant" },
    { name: "Manage Menu", path: "/restaurant/menu" },
    { name: "Manage Orders", path: "/restaurant/orders" },
    { name: "Order History", path: "/restaurant/history" },
    { name: "Account", path: "/restaurant/account" },
    { name: "Contact Us", path: "/restaurant/contact" },
    { name: "Logout", onClick: handleLogout },
  ];

  return (
    <div className="min-h-screen bg-amber-50">
      <Navbar links={navLinks} />

      <div className="max-w-xl mx-auto mt-10 p-6 bg-white rounded-2xl shadow-md border border-red-200">
        <h2 className="text-2xl font-bold mb-6 text-red-800">Restaurant Account</h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-red-900 mb-1">Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              readOnly
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-400 bg-gray-100"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-red-900 mb-1">Email</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              readOnly
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-400 bg-gray-100"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-red-900 mb-1">Phone</label>
            <input
              type="text"
              name="phone"
              value={formData.phone}
              readOnly
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-400 bg-gray-100"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-red-900 mb-1">Address</label>
            <input
              type="text"
              name="address"
              value={formData.address}
              onChange={handleChange}
              placeholder="Enter restaurant address"
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-400"
            />
          </div>

          <button
            type="submit"
            className="w-full mt-4 bg-red-800 text-white py-2 rounded-md hover:bg-red-900 transition-all"
          >
            Save
          </button>
        </form>
      </div>
    </div>
  );
}

export default R_Restaurant_Account;
