import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext'; // adjust the path if needed
import Navbar from '../components/Navbar';
import { toast } from 'react-toastify';

function D_Delivery_Dashboard() {
  const { logout, user } = useAuth();
  const navigate = useNavigate();

  // Optional: Protect route so only delivery users can access
  useEffect(() => {
    if (!user || user.role !== 'delivery') {
      navigate('/');
    }
  }, [user, navigate]);

  const handleLogout = () => {
    toast.success(`${user.name} logged out`);
    logout();
  };

  const links = [

    { name: 'Account', path: '/delivery/account' },
    { name: 'Contact Us', path: '/delivery/contact' },
    { name: 'Logout', onClick: handleLogout },
  ];

  return (
    <>
      <Navbar links={links} />

      <div className="min-h-screen bg-amber-50 text-gray-800 p-6">
        <h1 className="text-3xl font-bold text-red-900 mb-2">
          Welcome to Delivery Dashboard 🛵
        </h1>
        <p className="text-lg">Track and manage your deliveries effectively and quickly.</p>

        <div className="mt-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          <div className="p-4 bg-white shadow-md rounded-xl border border-red-200 hover:shadow-xl transition duration-200">
            <h2 className="font-semibold text-xl text-red-800">Pick Up Orders</h2>
            <p className="text-sm mt-1">See all orders that are ready for pickup.</p>
          </div>
          <div className="p-4 bg-white shadow-md rounded-xl border border-red-200 hover:shadow-xl transition duration-200">
            <h2 className="font-semibold text-xl text-red-800">Pending Orders</h2>
            <p className="text-sm mt-1">View orders that are yet to be delivered.</p>
          </div>
          <div className="p-4 bg-white shadow-md rounded-xl border border-red-200 hover:shadow-xl transition duration-200">
            <h2 className="font-semibold text-xl text-red-800">Order History</h2>
            <p className="text-sm mt-1">Check your completed deliveries and history.</p>
          </div>
        </div>
      </div>
    </>
  );
}

export default D_Delivery_Dashboard;
