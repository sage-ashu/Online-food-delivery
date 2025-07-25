import React, { useState } from 'react';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import Navbar from '../components/Navbar';
 // Adjust if needed
 // Adjust path if different

function R_Contact_us() {
  const [restaurantName, setRestaurantName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const navigate = useNavigate();
  const { logout } = useAuth(); // ✅ Access logout from context

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log("Restaurant Contact Submission:");
    console.log("Restaurant Name:", restaurantName);
    console.log("Email:", email);
    console.log("Message:", message);

    toast.success("Thanks for reaching out 🍽️");

    setRestaurantName('');
    setEmail('');
    setMessage('');
  };

  // ✅ Context-based logout with redirect
  const handleLogout = () => {
    logout();           // Clears user & shows toast
    navigate('/login'); // Redirect to login page
  };

  const navLinks = [
    { name: "Home", path: "/restaurant" },
    { name: "Manage Menu", path: "/restaurant/menu" },
    { name: "Manage Orders", path: "/restaurant/orders" },
    { name: "Order History", path: "/restaurant/history" },
    { name: "Account", path: "/restaurant/account" },
    { name: "Contact Us", path: "/restaurant/contact" },
    { name: "Logout", onClick: handleLogout },
  ];

  return (
    <div className="min-h-screen bg-amber-50">
      <Navbar links={navLinks} />

      <div className="flex items-center justify-center px-4 py-10">
        <div className="max-w-xl w-full bg-white rounded-2xl shadow-lg p-8">
          <h2 className="text-3xl font-bold text-red-900 mb-6 text-center">
            Restaurant Contact Us
          </h2>

          <form className="space-y-4" onSubmit={handleSubmit}>
            <div>
              <label className="block text-red-900 font-semibold">Restaurant Name</label>
              <input
                type="text"
                value={restaurantName}
                onChange={(e) => setRestaurantName(e.target.value)}
                placeholder="Your restaurant name"
                className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-900"
              />
            </div>

            <div>
              <label className="block text-red-900 font-semibold">Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Your email"
                className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-900"
              />
            </div>

            <div>
              <label className="block text-red-900 font-semibold">Message</label>
              <textarea
                rows="5"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                placeholder="Your message"
                className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-900"
              ></textarea>
            </div>

            <button
              type="submit"
              className="w-full bg-red-900 text-white py-3 rounded-md hover:bg-red-800 transition duration-300"
            >
              Send Message
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default R_Contact_us;
