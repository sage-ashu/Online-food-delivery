import React from "react";
import { toast } from "react-toastify";
import { useAuth } from "../context/AuthContext"; // adjust the path as needed

const MenuItemCard = ({ item }) => {
  const { authUser } = useAuth(); // Get the currently logged-in user

  const handleAddToCart = () => {
    if (!authUser) {
      toast.error("Please log in to add items to your cart.", {
        position: "bottom-right", // Optional: if you want position override
      });
      return;
    }

    // Later: Replace with dispatch(cartActions.add(item)) or POST to backend
    toast.success(`Added ${item.name} to cart!`, {
      position: "bottom-right", // Optional: for consistency
    });
  };

  return (
    <div className="bg-white rounded-xl shadow-md hover:shadow-xl transition duration-300 p-4 flex flex-col items-center text-center">
      <img
        src={item.image}
        alt={item.name}
        className="w-32 h-32 object-cover rounded-full mb-4 border-4 border-orange-100"
      />
      <h3 className="text-lg font-semibold text-orange-700">{item.name}</h3>
      <p className="text-gray-600 text-sm mb-2">{item.description}</p>
      <p className="text-orange-600 font-bold text-md mb-3">₹{item.price}</p>
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
