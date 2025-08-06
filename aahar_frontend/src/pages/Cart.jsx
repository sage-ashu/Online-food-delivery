// src/pages/Cart.jsx
import React from "react";
import { useCart } from "../context/CartContext";
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import CartItemCard from "../components/CartItemCard";

const Cart = () => {
  const { cartItems, removeFromCart, clearCart } = useCart();
  const navigate = useNavigate();

  const totalAmount = cartItems.reduce(
    (sum, item) => sum + item.dishPrice * item.quantity,
    0
  );

  return (
    <div className="min-h-screen bg-orange-50">
      <Navbar />
      <div className="max-w-3xl mx-auto p-6">
        {cartItems.length === 0 ? (
          <div className="text-center mt-24 text-gray-500 text-xl animate-pulse">
            🛒 Your cart is empty. Go grab something tasty!
          </div>
        ) : (
          <>
            <h2 className="text-3xl font-bold mb-6 text-orange-700">
              🛒 Your Cart
            </h2>

            {cartItems.map((item) => (
              <CartItemCard
                key={item.id}
                item={item}
                onRemove={removeFromCart}
              />
            ))}

            <div className="mt-6 flex justify-between items-center text-lg font-medium text-gray-700 border-t pt-4">
              <span>Total:</span>
              <span className="text-orange-700 font-bold text-xl">
                ₹{totalAmount}
              </span>
            </div>

            <div className="text-right mt-8 flex justify-end items-center gap-4">
              <button
                onClick={clearCart}
                className="text-sm text-red-600 underline hover:text-red-800"
              >
                Clear Cart
              </button>
              <button
                onClick={() => {
                  // Add slight delay if you want visual feedback
                  navigate("/checkout");
                }}
                className="bg-green-600 text-white px-6 py-2 rounded-full hover:bg-green-700 transition"
              >
                Checkout
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default Cart;
