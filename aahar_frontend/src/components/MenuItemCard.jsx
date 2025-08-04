import React from "react";
import { toast } from "react-toastify";
import { useAuth } from "../context/AuthContext";

// Base URL of your backend (Spring Boot app)
const BASE_IMAGE_URL = "http://localhost:9090/images/dishes/";

const MenuItemCard = ({ item }) => {
  const { authUser } = useAuth();

  const handleAddToCart = () => {
    if (!authUser) {
      toast.error("Please log in to add items to your cart.", {
        position: "bottom-right",
      });
      return;
    }

    toast.success(`Added ${item.dishName} to cart!`, {
      position: "bottom-right",
    });
  };

  return (
    <div className="bg-white rounded-xl shadow-md hover:shadow-xl transition duration-300 p-4 flex flex-col text-left">
      <img
        src={`${BASE_IMAGE_URL}${item.imagePath.split("\\").pop()}`}
        alt={item.dishName}
        className="w-full h-48 object-cover rounded-md mb-4"
        onError={(e) => {
          e.target.onerror = null;
          e.target.src = "/placeholder.jpg"; // fallback image
        }}
      />
      <h3 className="text-lg font-semibold text-orange-700">{item.dishName}</h3>
      <p className="text-gray-600 text-sm mb-2">{item.description}</p>
      <p className="text-orange-600 font-bold text-md mb-3">₹{item.dishPrice}</p>
      <button
        onClick={handleAddToCart}
        className="px-4 py-2 bg-orange-500 text-white rounded-full hover:bg-orange-600 transition duration-300"
      >
        Add to Cart
      </button>
    </div>
  );
};

export default MenuItemCard;
