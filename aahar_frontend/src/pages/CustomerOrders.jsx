import React from "react";
import { motion } from "framer-motion";
import { format } from "date-fns";
import Navbar from "../components/Navbar"; // ✅ Adjust path if needed

const mockOrders = [
  {
    id: "ORD123",
    restaurant: "Pizza Palace",
    items: [
      { name: "Margherita Pizza", quantity: 2 },
      { name: "Garlic Bread", quantity: 1 },
    ],
    total: 499,
    date: new Date(),
    status: "Delivered",
  },
  {
    id: "ORD124",
    restaurant: "Burger Hub",
    items: [
      { name: "Veg Burger", quantity: 1 },
      { name: "Fries", quantity: 1 },
    ],
    total: 299,
    date: new Date(),
    status: "In Progress",
  },
];

const navLinks = [
  { label: "Home", path: "/" },
  { label: "Menu", path: "/menu" },
  { label: "Cart", path: "/cart" },
  { label: "Orders", path: "/customer-orders" },
  { label: "Contact", path: "/contact" },
];

const CustomerOrders = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-yellow-50 to-orange-100">
      {/* ✅ Navbar */}
      <Navbar links={navLinks} />

      <main className="p-6">
        <h1 className="text-3xl font-bold text-center text-orange-600 mb-8">
          Your Orders
        </h1>

        {/* 🗺️ Map Section */}
        <motion.div
          className="max-w-5xl mx-auto mb-10 overflow-hidden rounded-2xl border-4 border-orange-300 shadow-lg"
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.6 }}
        >
          <iframe
            title="Delivery Location"
            src="https://maps.google.com/maps?q=MG%20Road%20Bangalore&t=&z=13&ie=UTF8&iwloc=&output=embed"
            width="100%"
            height="300"
            style={{ border: 0 }}
            allowFullScreen=""
            loading="lazy"
          ></iframe>
        </motion.div>

        {/* 📦 Orders List */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-5xl mx-auto">
          {mockOrders.map((order, index) => (
            <motion.div
              key={order.id}
              initial={{ opacity: 0, y: 50 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: index * 0.2 }}
              whileHover={{ scale: 1.02 }}
              className="bg-white p-6 rounded-2xl shadow-md hover:shadow-xl transition-all"
            >
              <div className="flex justify-between items-center mb-4">
                <div>
                  <h2 className="text-xl font-semibold text-orange-700">
                    {order.restaurant}
                  </h2>
                  <p className="text-sm text-gray-500">Order ID: {order.id}</p>
                </div>
                <span
                  className={`px-3 py-1 text-sm rounded-full ${
                    order.status === "Delivered"
                      ? "bg-green-100 text-green-700"
                      : "bg-yellow-100 text-yellow-700"
                  }`}
                >
                  {order.status}
                </span>
              </div>

              <ul className="mb-4">
                {order.items.map((item, idx) => (
                  <li
                    key={idx}
                    className="flex justify-between py-1 text-gray-700"
                  >
                    <span>{item.name}</span>
                    <span className="text-gray-500">x{item.quantity}</span>
                  </li>
                ))}
              </ul>

              <div className="flex justify-between text-sm text-gray-600">
                <span>Total: ₹{order.total}</span>
                <span>{format(order.date, "dd MMM yyyy, hh:mm a")}</span>
              </div>
            </motion.div>
          ))}
        </div>
      </main>
    </div>
  );
};

export default CustomerOrders;
