import React from "react";
import Navbar from "../components/Navbar";


function About() {
  const navLinks = [
    { label: "Home", path: "/" },
    { label: "Cart", path: "/cart" },
    { label: "Login", path: "/login" },
    { label: "Register", path: "/register" },
    { label: "About Us", path: "/about" },
    { label: "Contact Us", path: "/contact" },
  ];

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar links={navLinks} />

      <main className="flex-grow bg-gradient-to-br from-orange-50 to-orange-100 px-4 py-10">
        <div className="max-w-3xl mx-auto bg-white p-10 md:p-12 rounded-2xl shadow-2xl">
          <h2 className="text-4xl font-extrabold mb-6 text-center text-orange-700">
            About Us
          </h2>
          <p className="text-gray-700 text-lg leading-relaxed mb-4">
            Welcome to{" "}
            <span className="font-semibold text-orange-700">Aahar</span> — your
            trusted destination for fresh, flavorful meals delivered right to
            your door.
          </p>
          <p className="text-gray-700 text-lg leading-relaxed mb-4">
            At <span className="font-semibold text-orange-700">Aahar</span>, we
            combine technology with taste. From ordering your favorite cuisine
            to real-time order tracking and secure payments, our platform makes
            food delivery smooth and reliable.
          </p>
          <p className="text-gray-700 text-lg leading-relaxed mb-4">
            Whether you're working late, hosting a party, or simply craving
            comfort food, we’ve got you covered. Our partners are handpicked and
            food is always delivered hot and fresh.
          </p>
          <p className="text-gray-700 text-lg leading-relaxed">
            Thank you for making{" "}
            <span className="font-semibold text-orange-700">Aahar</span> a part
            of your daily life. We promise to keep improving and serving with
            love.
          </p>
        </div>
      </main>


    </div>
  );
}

export default About;
