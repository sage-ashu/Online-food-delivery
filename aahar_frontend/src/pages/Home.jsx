import React from "react";
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext"; // ✅ Import auth context

const Home = () => {
  const navigate = useNavigate();
  const { authUser, logout } = useAuth(); // ✅ Get auth user & logout

  // ✅ Dynamically build navbar links
  const navLinks = authUser
    ? [
        { label: "Home", path: "/" },
        { label: "Menu", path: "/menu" },
        { label: "Cart", path: "/cart" },
        { label: "Contact Us", path: "/contact" },
        { label: "Logout", action: logout },
      ]
    : [
        { label: "Home", path: "/" },
        { label: "Menu", path: "/menu" },
        { label: "Login", path: "/login" },
        { label: "Register", path: "/register" },
        { label: "Contact Us", path: "/contact" },
      ];

  const navigateToMenu = () => {
    navigate("/menu");
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-orange-50 to-white">
      <Navbar links={navLinks} />

      {/* Hero Section */}
      <section className="relative h-[80vh] flex items-center justify-center bg-gradient-to-r from-orange-400 to-red-500 text-white px-6 text-center">
        <div className="space-y-6 max-w-3xl">
          <h1 className="text-4xl md:text-6xl font-bold drop-shadow-lg animate-fadeIn">
            Welcome to Aahar 🍽️
          </h1>
          <p className="text-xl md:text-2xl font-medium opacity-90">
            Fresh, fast, and fabulous meals delivered to your doorstep.
          </p>
          <button
            className="bg-white text-orange-600 px-8 py-3 rounded-full font-semibold hover:bg-orange-100 transition duration-300 shadow-lg"
            onClick={navigateToMenu}
          >
            Explore Menu
          </button>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16 px-4 sm:px-8 bg-white">
        <div className="max-w-6xl mx-auto text-center">
          <h2 className="text-3xl font-bold text-orange-700 mb-4">Why Choose Us?</h2>
          <p className="text-gray-600 mb-10">
            Discover what makes Aahar the go-to food delivery app for thousands.
          </p>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {[
              {
                icon: "🚀",
                title: "Lightning Fast Delivery",
                desc: "Get piping hot food in under 30 minutes.",
              },
              {
                icon: "🍱",
                title: "Curated Menu",
                desc: "Dishes from top chefs across multiple cuisines.",
              },
              {
                icon: "💯",
                title: "Premium Quality",
                desc: "Fresh ingredients, always. No compromises.",
              },
            ].map((item, index) => (
              <div
                key={index}
                className="bg-gradient-to-tr from-orange-100 to-orange-50 p-6 rounded-xl shadow-md hover:scale-[1.03] transition-transform duration-300"
              >
                <div className="text-5xl mb-4">{item.icon}</div>
                <h3 className="text-xl font-semibold text-orange-700 mb-2">
                  {item.title}
                </h3>
                <p className="text-gray-700">{item.desc}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Testimonials / Info Section */}
      <section className="py-16 bg-gradient-to-br from-white to-orange-100 px-6">
        <div className="max-w-5xl mx-auto text-center">
          <h2 className="text-3xl font-bold text-orange-800 mb-8">Our Promise</h2>
          <p className="text-gray-700 text-lg mb-12">
            We work with local chefs and fresh vendors to ensure every bite is a delight.
          </p>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {[
              "Fresh Ingredients Daily",
              "Chef Curated Meals",
              "Safe & Contactless Delivery",
              "24/7 Customer Support",
              "Easy Refund Policy",
              "Exciting Offers & Coupons",
            ].map((point, i) => (
              <div
                key={i}
                className="bg-white p-5 rounded-lg shadow hover:shadow-xl transition duration-300"
              >
                <p className="text-orange-600 font-semibold">{point}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Call to Action */}
      <section className="py-20 px-6 bg-orange-600 text-white text-center">
        <div className="max-w-3xl mx-auto space-y-6">
          <h2 className="text-4xl font-bold">Hungry Already?</h2>
          <p className="text-lg opacity-90">
            Start your food journey with Aahar and enjoy exclusive discounts today!
          </p>
          <button
            className="bg-white text-orange-700 px-10 py-4 rounded-full font-semibold hover:bg-orange-100 transition duration-300"
            onClick={navigateToMenu}
          >
            Order Now
          </button>
        </div>
      </section>
    </div>
  );
};

export default Home;
