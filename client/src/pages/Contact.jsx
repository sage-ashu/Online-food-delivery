import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import { useAuth } from '../contexts/AuthContext';
import { toast } from 'react-toastify';

function Contact() {
  const { user, logout } = useAuth();

  const links = user
    ? [
        { name: user.name, path: "/account" },
        
        { name: "Home", path: "/" },
        { name: "Cart", path: "/cart" },
        { name: "Contact Us", path: "/contact" },
        { name: "Logout", path: "#", onClick: logout },
      ]
    : [
        { name: "Home", path: "/" },
        { name: "Login", path: "/login" },
        { name: "Register", path: "/register" },
        { name: "Contact Us", path: "/contact" },
      ];

  // States for form input
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log("Contact Form Submission:");
    console.log("Name:", name);
    console.log("Email:", email);
    console.log("Message:", message);

    toast.success("Your feedback is valuable ❤️");

    // Optional: Clear form after submission
    setName('');
    setEmail('');
    setMessage('');
  };

  return (
    <>
      <Navbar links={links} />

      <div className="min-h-[calc(100vh-4rem)] bg-amber-50 flex items-center justify-center px-4 py-10">
        <div className="max-w-xl w-full bg-white rounded-2xl shadow-lg p-8">
          <h2 className="text-3xl font-bold text-red-900 mb-6 text-center">Contact Us</h2>

          <form className="space-y-4" onSubmit={handleSubmit}>
            <div>
              <label className="block text-red-900 font-semibold">Name</label>
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Your name"
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
    </>
  );
}

export default Contact;
