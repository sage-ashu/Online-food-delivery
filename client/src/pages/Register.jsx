import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { registerUser } from "../services/userService";
import { registerRestaurant } from "../services/restaurantOwnerService";
import { registerDeliveryAgent } from "../services/deliveryService";
import { toast } from "react-toastify";

const Register = () => {
  const navigate = useNavigate();

  const [accountType, setAccountType] = useState("user");
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleTypeChange = (e) => {
    setAccountType(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (accountType === "user") {
        await registerUser(formData);
      } else if (accountType === "restaurant") {
        await registerRestaurant(formData);
      } else if (accountType === "delivery") {
        await registerDeliveryAgent(formData);
      }

      toast.success(`${accountType} registered successfully`);
      navigate("/login");
    } catch (err) {
      console.error(err);
      toast.error(err.message || "Registration failed");
    }
  };

  return (
    <>
      {/* Back to Home Button */}
      <div className="absolute top-4 left-4">
        <Link
          to="/"
          className="bg-amber-900 text-white px-4 py-2 rounded-full shadow hover:bg-amber-800 transition duration-200"
        >
          ⬅ Back to Home
        </Link>
      </div>

      <div className="min-h-screen bg-amber-50 flex items-center justify-center p-6">
        <div className="bg-white shadow-2xl rounded-2xl p-8 max-w-md w-full">
          <h2 className="text-3xl font-bold text-center text-amber-900 mb-6">
            Create your Aahar Account 🍽️
          </h2>

          <form onSubmit={handleSubmit} className="space-y-4">
            {/* Role Selector */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-1">
                Register as:
              </label>
              <select
                value={accountType}
                onChange={handleTypeChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
              >
                <option value="user">Customer</option>
                <option value="restaurant">Restaurant</option>
                <option value="delivery">Delivery Agent</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-1">
                Name
              </label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-1">
                Email
              </label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-1">
                Phone
              </label>
              <input
                type="text"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-1">
                Password
              </label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            <button
              type="submit"
              className="w-full bg-amber-900 hover:bg-amber-800 text-white py-2 rounded-lg transition duration-200"
            >
              Register
            </button>
          </form>

          {/* Already have an account link */}
          <p className="mt-4 text-center text-sm text-gray-700">
            Already have an account?{" "}
            <Link to="/login" className="text-amber-900 font-semibold hover:underline">
              Login here
            </Link>
          </p>
        </div>
      </div>
    </>
  );
};

export default Register;
