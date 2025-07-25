import React from "react";
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";

function R_Manage_Orders() {
  const navigate = useNavigate();
  const { logout } = useAuth();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const links = [
    { name: "Home", path: "/restaurant" },
    { name: "Manage Menu", path: "/restaurant/menu" },
    { name: "Manage Orders", path: "/restaurant/orders" },
    { name: "Order History", path: "/restaurant/history" },
    { name: "Account", path: "/restaurant/account" },
    { name: "Contact Us", path: "/restaurant/contact" },
    { name: "Logout", onClick: handleLogout },
  ];

  return (
    <>
      <Navbar links={links} />

      {/* Updated background color to match R_Manage_Menu */}
      <div className="flex flex-col lg:flex-row gap-6 px-6 py-10 bg-amber-50 min-h-screen">
        {/* Left panel (filters or status sidebar) */}
        <div className="w-full lg:w-1/2 p-6 bg-white rounded-xl shadow-md border border-gray-200">
          <h2 className="text-2xl font-bold text-red-800 mb-4">Order Filters</h2>
          <p className="text-gray-600">Add status tabs here like Pending, Accepted, Delivered, etc.</p>
        </div>

        {/* Right panel (orders list) */}
        <div className="w-full lg:w-1/2 space-y-4">
          <h2 className="text-2xl font-bold text-red-800 mb-4">Customer Orders</h2>

          {/* Order card sample */}
          <div className="bg-white p-4 rounded-xl border shadow-sm space-y-2">
            <div className="flex justify-between items-center">
              <h3 className="text-lg font-semibold text-red-700">Order #12345</h3>
              <span className="text-sm text-yellow-600 font-medium">Pending</span>
            </div>
            <p className="text-sm text-gray-600">Items: Paneer Pizza x1, Coke x1</p>
            <p className="text-sm text-gray-600">Customer: Alice Sharma</p>
            <p className="text-sm text-gray-600">Total: ₹450</p>
            <div className="flex gap-2 mt-2">
              <button className="px-3 py-1 bg-green-500 text-white rounded hover:bg-green-600 text-sm">
                Accept
              </button>
              <button className="px-3 py-1 bg-red-500 text-white rounded hover:bg-red-600 text-sm">
                Reject
              </button>
            </div>
          </div>

          <p className="text-gray-500">More orders will appear here.</p>
        </div>
      </div>
    </>
  );
}

export default R_Manage_Orders;
