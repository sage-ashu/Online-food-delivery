import React, { useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../contexts/AuthContext';
import Navbar from '../components/Navbar';

const R_RestaurantDashboard = () => {
  const { logout, user } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== 'restaurant') {
      navigate('/');
    }
  }, [user, navigate]);

  const handleLogout = () => {
    toast.success(`${user.name} logged out`);
    logout();
  };

  const links = [
    { name: 'Home', path: '/restaurant' },
    { name: 'Manage Menu', path: '/restaurant/menu' },
    { name: 'Manage Orders', path: '/restaurant/orders' },
    { name: 'Order History', path: '/restaurant/history' },
    { name: 'Account', path: '/restaurant/account' },
    { name: 'Contact Us', path: '/restaurant/contact' },
    { name: 'Logout', onClick: handleLogout },
  ];

  return (
    <>
      <Navbar links={links} />
      <div className="bg-amber-50 min-h-screen p-6">
        <h2 className="text-3xl font-bold text-red-900">Welcome to Restaurant Dashboard 👨‍🍳</h2>
        <p className="mt-2 text-gray-700">
          Manage your menu, view placed orders, and update your account here.
        </p>

        {/* Cards Section */}
        <div className="mt-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          <Link
            to="/restaurant/menu"
            className="p-4 bg-white shadow-md rounded-xl border border-red-200 hover:shadow-xl transition duration-200"
          >
            <h2 className="font-semibold text-xl text-red-800">Manage Menu</h2>
            <p className="text-sm mt-1">
              Add, update, or remove food items from your restaurant's menu.
            </p>
          </Link>

          <Link
            to="/restaurant/orders"
            className="p-4 bg-white shadow-md rounded-xl border border-red-200 hover:shadow-xl transition duration-200"
          >
            <h2 className="font-semibold text-xl text-red-800">Placed Orders</h2>
            <p className="text-sm mt-1">
              Check all incoming orders placed by customers.
            </p>
          </Link>

          <Link
            to="/restaurant/history"
            className="p-4 bg-white shadow-md rounded-xl border border-red-200 hover:shadow-xl transition duration-200"
          >
            <h2 className="font-semibold text-xl text-red-800">Order History</h2>
            <p className="text-sm mt-1">
              Track fulfilled and past orders for your restaurant.
            </p>
          </Link>
        </div>
      </div>
    </>
  );
};

export default R_RestaurantDashboard;
