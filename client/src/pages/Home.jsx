import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import MenuCard from '../components/MenuCard';
import { useAuth } from '../contexts/AuthContext';
import { useMenu } from '../contexts/MenuContext'; // ✅ import context

function Home() {
  const { user, logout } = useAuth();
  const { menuItems } = useMenu(); // ✅ get data from context

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

  const postPerPage = 6;
  const [currentPage, setCurrentPage] = useState(1);
  const lastItemIndex = currentPage * postPerPage;
  const firstItemIndex = lastItemIndex - postPerPage;
  const selectedItems = menuItems.slice(firstItemIndex, lastItemIndex); // ✅ use context menu

  return (
    <>
      <Navbar links={links} />

      <div className="pt-4 px-10 bg-amber-50 min-h-screen">
        <h2 className="text-2xl font-bold mb-4 text-red-900">Our Menu</h2>

        {menuItems.length === 0 ? (
          <p className="text-gray-500">No menu items available.</p>
        ) : (
          <>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              {selectedItems.map((item, index) => (
                <MenuCard key={index} item={item} />
              ))}
            </div>

            <div className="flex justify-center mt-6 space-x-2">
              {Array.from({ length: Math.ceil(menuItems.length / postPerPage) }).map((_, index) => (
                <button
                  key={index}
                  onClick={() => setCurrentPage(index + 1)}
                  className={`px-4 py-2 rounded ${
                    currentPage === index + 1
                      ? 'bg-red-900 text-white'
                      : 'bg-white text-red-900 border'
                  }`}
                >
                  {index + 1}
                </button>
              ))}
            </div>
          </>
        )}
      </div>

      <Footer />
    </>
  );
}

export default Home;
