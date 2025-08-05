import React from "react";
import { useCart } from "../context/CartContext";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { createOrder } from "../services/orderService";

const Checkout = () => {
  const { cartItems, clearCart } = useCart();
  const navigate = useNavigate();

  const calculateTotal = () => {
    return cartItems.reduce(
      (acc, item) => acc + item.dishPrice * item.quantity,
      0
    );
  };

  const handlePlaceOrder = async () => {
    try {
      const orderData = {
        customerId: currentUser.id,
        restaurantId: cart.restaurantId,
        items: cart.items.map(item => ({
          dishId: item.id,
          quantity: item.quantity,
        })),
        // remove razorpay fields for now
      };
  
      const response = await createOrder(orderData);
      console.log("Order placed:", response);
      toast.success("Order placed successfully!");
      // navigate or clear cart, etc.
    } catch (error) {
      console.error("Error placing order:", error);
      toast.error("Failed to place order.");
    }
  };

  if (cartItems.length === 0) {
    return (
      <div className="min-h-screen bg-orange-50">
        <Navbar />
        <div className="text-center mt-20 text-gray-500 text-lg">
          🧾 Nothing to checkout. Your cart is empty.
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-orange-50">
      <Navbar />
      <div className="max-w-3xl mx-auto p-6">
        <h2 className="text-3xl font-bold mb-6 text-orange-700">
          🧾 Checkout
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
            <div className="text-orange-600 font-semibold text-md">
              ₹{item.dishPrice * item.quantity}
            </div>
          </div>
        ))}

        <div className="mt-6 flex justify-between items-center text-lg font-medium text-gray-700">
          <span>Total:</span>
          <span className="text-orange-700 font-bold text-xl">
            ₹{calculateTotal()}
          </span>
        </div>

        <div className="text-right mt-8">
          <button
            onClick={handlePlaceOrder}
            className="bg-green-600 text-white px-6 py-2 rounded-full hover:bg-green-700"
          >
            Place Order
          </button>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
