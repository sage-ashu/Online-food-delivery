import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { registerRestaurant } from "../services/registrationService";
import { toast } from "react-toastify";

function Register() {
  const navLinks = [
    { label: "Home", path: "/" },
    { label: "Cart", path: "/cart" },
    { label: "Login", path: "/login" },
    { label: "Register", path: "/register" },
    { label: "About Us", path: "/about" },
    { label: "Contact Us", path: "/contact" },
  ];

  const navigate = useNavigate();

  const [role, setRole] = useState("customer");
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    name: "",
    phoneNumber: "",
  });

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (role === "restaurant") {
      const restaurantData = {
        name: formData.name,
        phoneNumber: formData.phoneNumber,
        email: formData.email,
        password: formData.password,
      };

      const result = await registerRestaurant(restaurantData);

      if (result.success) {
        toast.success("Restaurant registered successfully!", {
          position: "bottom-right",
        });
        navigate("/login");
      } else {
        toast.error(result.message || "Registration failed", {
          position: "bottom-right",
        });
      }
    } else {
      // You can implement customer registration here if needed later
      toast.info("Customer registration is not yet implemented.", {
        position: "bottom-right",
      });
    }
  };

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar links={navLinks} />

      <main className="flex-grow bg-gradient-to-br from-orange-50 to-orange-100 px-4 py-10">
        <div className="max-w-md mx-auto bg-white p-8 md:p-10 rounded-2xl shadow-2xl">
          <h2 className="text-3xl font-bold mb-6 text-center text-orange-700">
            Register with Aahar
          </h2>

          {/* Toggle */}
          <div className="flex justify-center mb-6">
            <button
              className={`px-4 py-2 rounded-l-xl font-medium transition ${
                role === "customer"
                  ? "bg-orange-600 text-white"
                  : "bg-orange-100 text-orange-600"
              }`}
              onClick={() => setRole("customer")}
            >
              Customer
            </button>
            <button
              className={`px-4 py-2 rounded-r-xl font-medium transition ${
                role === "restaurant"
                  ? "bg-orange-600 text-white"
                  : "bg-orange-100 text-orange-600"
              }`}
              onClick={() => setRole("restaurant")}
            >
              Restaurant
            </button>
          </div>

          {/* Form */}
          <form onSubmit={handleSubmit}>
            {/* Conditional Fields */}
            {role === "customer" ? (
              <>
                <div className="mb-4">
                  <label className="block text-orange-700 mb-1">First Name</label>
                  <input
                    type="text"
                    name="firstName"
                    required
                    value={formData.firstName}
                    onChange={handleChange}
                    className="w-full px-4 py-2 border border-orange-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-400"
                  />
                </div>
                <div className="mb-4">
                  <label className="block text-orange-700 mb-1">Last Name</label>
                  <input
                    type="text"
                    name="lastName"
                    required
                    value={formData.lastName}
                    onChange={handleChange}
                    className="w-full px-4 py-2 border border-orange-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-400"
                  />
                </div>
              </>
            ) : (
              <>
                <div className="mb-4">
                  <label className="block text-orange-700 mb-1">Restaurant Name</label>
                  <input
                    type="text"
                    name="name"
                    required
                    value={formData.name}
                    onChange={handleChange}
                    className="w-full px-4 py-2 border border-orange-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-400"
                  />
                </div>
                <div className="mb-4">
                  <label className="block text-orange-700 mb-1">Phone Number</label>
                  <input
                    type="text"
                    name="phoneNumber"
                    required
                    value={formData.phoneNumber}
                    onChange={handleChange}
                    className="w-full px-4 py-2 border border-orange-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-400"
                  />
                </div>
              </>
            )}

            {/* Common Fields */}
            <div className="mb-4">
              <label className="block text-orange-700 mb-1">Email</label>
              <input
                type="email"
                name="email"
                required
                value={formData.email}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-orange-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-400"
              />
            </div>
            <div className="mb-6">
              <label className="block text-orange-700 mb-1">Password</label>
              <input
                type="password"
                name="password"
                required
                value={formData.password}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-orange-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-400"
              />
            </div>

            <button
              type="submit"
              className="w-full bg-orange-600 text-white font-semibold py-2 rounded-lg hover:bg-orange-700 transition"
            >
              Register as {role.charAt(0).toUpperCase() + role.slice(1)}
            </button>
          </form>

          {/* Login Redirect */}
          <div className="mt-6 text-center">
            <span className="text-gray-700">Already have an account? </span>
            <button
              onClick={() => navigate("/login")}
              className="text-orange-600 font-medium hover:underline"
            >
              Login
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Register;
