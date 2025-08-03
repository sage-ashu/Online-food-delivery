import React from 'react';
import Navbar from '../components/Navbar';


function Contact() {
  const navLinks = [
    { label: "Home", path: "/" },
    { label: "Cart", path: "/cart" },
    { label: "Login", path: "/login" },
    { label: "Register", path: "/register" },
    { label: "About Us", path: "/about" },
    { label: "Contact Us", path: "/contact" },
  ];

  return (
    <>
      <Navbar links={navLinks} />

      <main className="min-h-screen bg-gradient-to-br from-orange-50 to-orange-100 flex items-center justify-center px-4 py-12">
        <section className="max-w-2xl w-full bg-white p-8 md:p-10 rounded-2xl shadow-2xl">
          <h1 className="text-4xl font-extrabold text-orange-700 mb-8 text-center">
            Contact Us
          </h1>

          <form className="space-y-6" onSubmit={(e) => e.preventDefault()}>
            <div>
              <label htmlFor="name" className="block text-gray-700 font-semibold mb-1">
                Name
              </label>
              <input
                id="name"
                name="name"
                type="text"
                placeholder="Enter your name"
                className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-300"
                required
              />
            </div>

            <div>
              <label htmlFor="email" className="block text-gray-700 font-semibold mb-1">
                Email
              </label>
              <input
                id="email"
                name="email"
                type="email"
                placeholder="Enter your email"
                className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-300"
                required
              />
            </div>

            <div>
              <label htmlFor="message" className="block text-gray-700 font-semibold mb-1">
                Message
              </label>
              <textarea
                id="message"
                name="message"
                rows="5"
                placeholder="Write your message..."
                className="w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-300"
                required
              ></textarea>
            </div>

            <button
              type="submit"
              className="w-full py-3 bg-orange-700 text-white font-bold rounded-lg shadow-md hover:bg-orange-800 transition duration-300 ease-in-out"
            >
              Send Message
            </button>
          </form>
        </section>
      </main>


    </>
  );
}

export default Contact;
