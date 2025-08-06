import React, { useState } from "react";
import { toast } from "react-toastify";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";
import { ShoppingCart } from "lucide-react";

const BASE_IMAGE_URL = "http://localhost:9090/images/dishes/";

const MenuItemCard = ({ item }) => {
  const { authUser } = useAuth();
  const { addToCart } = useCart();
  const [quantity, setQuantity] = useState(1);

  // console.log("Dish Item:", item);

  const handleAddToCart = () => {
    if (!authUser) {
      toast.error("Please log in to add items to your cart.", {
        position: "bottom-right",
      });
      return;
    }
    if (!item.restaurantId) {
      toast.error("Invalid item data.");
      return;
    }
    

    addToCart(item, quantity);
  };

  return (
    <div className="bg-white rounded-2xl shadow-md hover:shadow-lg transition duration-300 overflow-hidden border border-gray-100">
      <div className="relative group">
        <img
          src={`${BASE_IMAGE_URL}${item.imagePath.split("\\").pop()}`}
          alt={item.dishName}
          className="w-full h-56 object-cover transition-transform duration-300 group-hover:scale-105"
          onError={(e) => {
            e.target.onerror = null;
            e.target.src = "/placeholder.jpg";
          }}
        />
        <div
          className={`absolute top-3 left-3 w-4 h-4 rounded-full ${
            item.veg ? "bg-green-600" : "bg-red-600"
          } border-2 border-white`}
          title={item.veg ? "Vegetarian" : "Non-Vegetarian"}
        ></div>
      </div>

      <div className="p-4 flex flex-col gap-2">
        <div className="flex justify-between items-start">
          <h3 className="text-lg font-semibold text-gray-800">{item.dishName}</h3>
          <span className="bg-orange-100 text-orange-600 text-sm font-semibold px-2 py-1 rounded-full">
            ₹{item.dishPrice}
          </span>
        </div>

        <p className="text-sm text-gray-500 line-clamp-2">{item.description}</p>
        <span className="text-xs bg-gray-100 text-gray-600 rounded-full px-2 py-0.5 w-fit">
          From: {item.restaurantName}
        </span>

        {/* Quantity Selector */}
        <div className="flex items-center mt-2 gap-2">
          <label className="text-sm text-gray-600">Qty:</label>
          <input
            type="number"
            min={1}
            value={quantity}
            onChange={(e) => setQuantity(Math.max(1, parseInt(e.target.value)))}
            className="w-16 p-1 text-center border rounded"
          />
        </div>

        <button
          onClick={handleAddToCart}
          className="mt-3 flex items-center justify-center gap-2 px-4 py-2 bg-orange-500 text-white font-medium rounded-full hover:bg-orange-600 transition duration-300"
        >
          <ShoppingCart size={18} />
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default MenuItemCard;
