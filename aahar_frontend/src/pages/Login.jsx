import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";
import { fetchCart } from "../services/cartService";
import { toast } from "react-toastify";

function Login() {
  const navLinks = [
    { label: "Home", path: "/" },
    { label: "Cart", path: "/cart" },
    { label: "Login", path: "/login" },
    { label: "Register", path: "/register" },
    { label: "About Us", path: "/about" },
    { label: "Contact Us", path: "/contact" },
  ];

  const navigate = useNavigate();
  const { login } = useAuth();
  const { setCartItems, setRestaurantId } = useCart(); // 👈 hook to sync cart
  const [role, setRole] = useState("customer");
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    const result = await login(formData.email, formData.password, role);

    if (result.success) {
      toast.success("Login successful!");

      if (role === "customer" && result.userData?.id) {
        try {
          const cartResponse = await fetchCart(result.userData.id);

          if (cartResponse?.data?.items?.length) {
            const backendItems = cartResponse.data.items.map((item) => ({
              id: item.dish.id,
              restaurantId: cartResponse.data.restaurant.id, // ✅ Correct
              dishName: item.dish.dishName,
              dishPrice: item.dish.dishPrice,
              quantity: item.quantity,
            }));
          

            setCartItems(backendItems);
            setRestaurantId(cartResponse.data.restaurant.id);
          } else {
            setCartItems([]);
            setRestaurantId(null);
          }
        } catch (error) {
          toast.error("Failed to load cart data.");
          console.error("Cart fetch error:", error);
        }
      }

      if (role === "restaurant") {
        navigate("/orders");
      } else {
        navigate("/menu");
      }
    } else {
      toast.error(result.message || "Login failed!");
    }
  };

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar links={navLinks} />
      <main className="flex-grow bg-gradient-to-br from-orange-50 to-orange-100 px-4 py-10">
        <div className="max-w-md mx-auto bg-white p-8 md:p-10 rounded-2xl shadow-2xl">
          <h2 className="text-3xl font-bold mb-6 text-center text-orange-700">
            Login to Aahar
          </h2>

          {/* Role Toggle */}
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

          {/* Login Form */}
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-orange-700 mb-1" htmlFor="email">
                Email
              </label>
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
              <label className="block text-orange-700 mb-1" htmlFor="password">
                Password
              </label>
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
              Login as {role.charAt(0).toUpperCase() + role.slice(1)}
            </button>
          </form>

          {/* Register Link */}
          <div className="mt-6 text-center">
            <span className="text-gray-700">Don't have an account? </span>
            <button
              onClick={() => navigate("/register")}
              className="text-orange-600 font-medium hover:underline"
            >
              Register
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Login;
