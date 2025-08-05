import React, { useEffect, useState } from "react";
import { getOrdersByCustomer } from "../services/orderService";
import { useAuth } from "../context/AuthContext";
import { motion } from "framer-motion";
import { Loader2 } from "lucide-react";
import Navbar from "../components/Navbar"; // 👈 make sure this path is correct

function CustomerOrders() {
  const { currentUser } = useAuth();
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  const navLinks = [
    { label: "Home", path: "/" },
    { label: "Menu", path: "/menu" },
    { label: "Cart", path: "/cart" },
    { label: "Orders", path: "/customer-orders" }, // 👈 This page
    { label: "Contact", path: "/contact" },
  ];

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const data = await getOrdersByCustomer(currentUser.id);
        setOrders(data);
      } catch (error) {
        console.error("Error fetching orders:", error);
      } finally {
        setLoading(false);
      }
    };

    if (currentUser?.id) {
      fetchOrders();
    }
  }, [currentUser]);

  return (
    <div className="min-h-screen bg-gradient-to-r from-yellow-50 to-pink-100">
      {/* ✅ Navbar */}
      <Navbar links={navLinks} />

      <main className="p-6">
        <h2 className="text-4xl font-bold text-center mb-8 text-gray-800">🍽️ Your Orders</h2>

        {loading ? (
          <div className="flex justify-center items-center mt-20">
            <Loader2 className="animate-spin w-10 h-10 text-pink-600" />
          </div>
        ) : orders.length === 0 ? (
          <div className="text-center text-xl text-gray-600 mt-16">You haven’t ordered anything yet.</div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {orders.map((order, index) => (
              <motion.div
                key={order.id}
                initial={{ y: 50, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                transition={{ delay: index * 0.1, type: "spring", stiffness: 100 }}
                className="bg-white shadow-xl rounded-2xl p-6 hover:shadow-2xl transition duration-300"
              >
                <div className="text-lg font-semibold text-pink-700 mb-2">Order #{order.id}</div>
                <div className="text-gray-600 mb-1">
                  Status:{" "}
                  <span className="font-medium text-green-600">{order.status}</span>
                </div>
                <div className="text-gray-600 mb-1">
                  Date: {new Date(order.createdAt).toLocaleString()}
                </div>
                <div className="text-gray-600 mb-2">
                  Restaurant:{" "}
                  <span className="font-medium">{order.restaurantName}</span>
                </div>
                <ul className="list-disc list-inside text-gray-700">
                  {order.items.map((item, idx) => (
                    <li key={idx}>
                      🍕 {item.dishName} x {item.quantity}
                    </li>
                  ))}
                </ul>
              </motion.div>
            ))}
          </div>
        )}
      </main>
    </div>
  );
}

export default CustomerOrders;
