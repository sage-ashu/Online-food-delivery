import React, { useState } from "react";
import Navbar from "../components/Navbar"; // Adjust path based on your folder structure
import { useNavigate } from "react-router-dom";

function R_Restaurant_Account() {
  const navigate = useNavigate();

  // Sample user data, ideally fetched from context or API
  const [formData, setFormData] = useState({
    name: "Aahar Restaurant",
    email: "aahar@example.com",
    address: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form Data:", formData);
  };

  const links = [
    { name: "Manage Orders", path: "/restaurant/orders" },
    { name: "Account", path: "/restaurant/account" },
    { name: "Logout", onClick: handleLogout },
  ];

  return (
    <div className="min-h-screen bg-red-50">
      <Navbar links={links} />

      <div className="max-w-md mx-auto mt-10 p-6 bg-white shadow-lg rounded-xl border border-red-200">
        <h2 className="text-2xl font-semibold text-red-900 mb-4">Restaurant Account</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-red-800 mb-1">Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              readOnly
              className="w-full px-4 py-2 border border-red-300 rounded-md bg-red-100 cursor-not-allowed"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-red-800 mb-1">Email</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              readOnly
              className="w-full px-4 py-2 border border-red-300 rounded-md bg-red-100 cursor-not-allowed"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-red-800 mb-1">Address</label>
            <textarea
              name="address"
              rows="3"
              value={formData.address}
              onChange={handleChange}
              placeholder="Enter your restaurant address"
              className="w-full px-4 py-2 border border-red-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-400"
            />
          </div>

          <button
            type="submit"
            className="w-full py-2 px-4 bg-red-800 text-white rounded-md hover:bg-red-700 transition duration-200"
          >
            Save
          </button>
        </form>
      </div>
    </div>
  );
}

export default R_Restaurant_Account;
