// src/pages/Checkout.jsx
import React, { useState } from "react";
import { useCart } from "../context/CartContext";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { createOrder } from "../services/orderService";
import { toast } from "react-hot-toast";
import { motion } from "framer-motion";

const Checkout = () => {
  const { cartItems, clearCart } = useCart();
  const navigate = useNavigate();
  const [isPlacing, setIsPlacing] = useState(false);

  const calculateTotal = () =>
    cartItems.reduce((acc, item) => acc + item.dishPrice * item.quantity, 0);

  const handlePayment = async () => {
    const totalAmount = calculateTotal();
    const currentUser = JSON.parse(localStorage.getItem("authUser"));

    const options = {
      key: "rzp_test_6c7w5jVrmCGqQ6",
      amount: totalAmount * 100, // in paisa
      currency: "INR",
      name: "Aahar Food App",
      description: "Order Payment",
      image: "/logo.png",
      prefill: {
        name: currentUser?.name,
        email: currentUser?.email,
        contact: "9999999999",
      },
      notes: {
        address: "Food Order from Aahar",
      },
      theme: {
        color: "#16a34a", // Tailwind green-600
      },
      handler: async function (response) {
        try {
          setIsPlacing(true);

          const orderData = {
            customerId: currentUser.id,
            restaurantId: cartItems[0]?.restaurantId,
            items: cartItems.map((item) => ({
              dishId: item.id,
              quantity: item.quantity,
            })),
          };

          const result = await createOrder(orderData);
          toast.success("🎉 Payment Successful & Order Placed!");
          clearCart();
          navigate("/orders");
        } catch (error) {
          console.error("Order creation failed:", error);
          toast.error("⚠️ Payment was successful, but order failed.");
        } finally {
          setIsPlacing(false);
        }
      },
      modal: {
        ondismiss: () => {
          toast.error("❌ Payment Cancelled");
        },
      },
    };

    const rzp = new window.Razorpay(options);
    rzp.open();

    rzp.on("payment.failed", function () {
      toast.error("❌ Payment Failed!");
    });
  };

  if (cartItems.length === 0) {
    return (
      <div className="min-h-screen bg-orange-50">
        <Navbar />
        <motion.div
          className="text-center mt-20 text-gray-500 text-lg"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
        >
          🧾 Nothing to checkout. Your cart is empty.
        </motion.div>
      </div>
    );
  }

  return (
    <motion.div
      className="min-h-screen bg-orange-50"
      initial={{ opacity: 0, y: 50 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.4 }}
    >
      <Navbar />
      <div className="max-w-3xl mx-auto p-6">
        <motion.h2
          className="text-3xl font-bold mb-6 text-orange-700"
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
        >
          🧾 Checkout
        </motion.h2>

        {cartItems.map((item, index) => (
          <motion.div
            key={item.id}
            className="border-b py-4 flex justify-between items-center"
            initial={{ opacity: 0, y: 10 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.05 * index }}
          >
            <div>
              <h4 className="font-semibold text-lg">{item.dishName}</h4>
              <p className="text-sm text-gray-500">Qty: {item.quantity}</p>
            </div>
            <div className="text-orange-600 font-semibold text-md">
              ₹{item.dishPrice * item.quantity}
            </div>
          </motion.div>
        ))}

        <motion.div
          className="mt-6 flex justify-between items-center text-lg font-medium text-gray-700 border-t pt-4"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.2 }}
        >
          <span>Total:</span>
          <span className="text-orange-700 font-bold text-xl">
            ₹{calculateTotal()}
          </span>
        </motion.div>

        <motion.div
          className="text-right mt-8"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
        >
          <button
            onClick={handlePayment}
            disabled={isPlacing}
            className={`bg-green-600 text-white px-6 py-2 rounded-full hover:bg-green-700 transition ${
              isPlacing ? "opacity-50 cursor-not-allowed" : ""
            }`}
          >
            {isPlacing ? "Placing Order..." : "Place Order & Pay"}
          </button>
        </motion.div>
      </div>
    </motion.div>
  );
};

export default Checkout;
