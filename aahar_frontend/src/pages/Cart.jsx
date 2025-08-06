// src/pages/Cart.jsx
import React from "react";
import { useCart } from "../context/CartContext";
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const { cartItems, removeFromCart, clearCart } = useCart();
  const navigate = useNavigate();

  if (cartItems.length === 0) {
    return (
      <div className="min-h-screen bg-orange-50">
        <Navbar />
        <div className="text-center mt-20 text-gray-500 text-lg">
          🛒 Your cart is empty.
        </div>
      </div>
    );
  }

  const totalAmount = cartItems.reduce(
    (sum, item) => sum + item.dishPrice * item.quantity,
    0
  );

  return (
    <div className="min-h-screen bg-orange-50">
      <Navbar />
      <div className="max-w-3xl mx-auto p-6">
        <h2 className="text-3xl font-bold mb-6 text-orange-700">
          🛒 Your Cart
        </h2>

        {cartItems.map((item) => (
          <div
            key={item.id}
            className="border-b py-4 flex justify-between items-center"
          >
            <div>
              <h4 className="font-semibold text-lg">{item.dishName}</h4>
              <p className="text-sm text-gray-500">Qty: {item.quantity}</p>
            </div>
            <div className="flex items-center gap-4">
              <span className="text-orange-600 font-semibold text-md">
                ₹{item.dishPrice * item.quantity}
              </span>
              <button
                onClick={() => removeFromCart(item.id)}
                className="text-red-500 text-sm hover:underline"
              >
                Remove
              </button>
            </div>
          </div>
        ))}

        <div className="mt-6 flex justify-between items-center text-lg font-medium text-gray-700">
          <span>Total:</span>
          <span className="text-orange-700 font-bold text-xl">
            ₹{totalAmount}
          </span>
        </div>

        <div className="text-right mt-8">
          <button
            onClick={clearCart}
            className="text-sm text-red-600 underline mr-4"
          >
            Clear Cart
          </button>
          <button
            onClick={() => navigate("/checkout")}
            className="bg-green-600 text-white px-6 py-2 rounded-full hover:bg-green-700"
          >
            Checkout
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
