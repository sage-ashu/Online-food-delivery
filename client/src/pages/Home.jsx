import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import MenuCard from '../components/MenuCard';
import { useAuth } from '../contexts/AuthContext';

function Home() {
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

  const items = [
    {
      name: 'Pizza Margherita',
      description: 'Classic cheese pizza with fresh tomatoes and basil.',
      price: 250,
      image: 'https://source.unsplash.com/400x300/?pizza',
    },
    {
      name: 'Veg Burger',
      description: 'Crispy patty with lettuce and mayo.',
      price: 120,
      image: 'https://source.unsplash.com/400x300/?burger',
    },
    {
      name: 'White Sauce Pasta',
      description: 'Creamy and cheesy Italian pasta delight.',
      price: 200,
      image: 'https://source.unsplash.com/400x300/?pasta',
    },
    {
      name: 'French Fries',
      description: 'Golden crispy fries with ketchup.',
      price: 80,
      image: 'https://source.unsplash.com/400x300/?fries',
    },
    {
      name: 'Green Salad',
      description: 'Fresh greens with olive oil and herbs.',
      price: 90,
      image: 'https://source.unsplash.com/400x300/?salad',
    },
    {
      name: 'Hakka Noodles',
      description: 'Spicy Indo-Chinese noodles with veggies.',
      price: 180,
      image: 'https://source.unsplash.com/400x300/?noodles',
    },
    {
      name: 'Spring Rolls',
      description: 'Crispy vegetable spring rolls.',
      price: 150,
      image: 'https://source.unsplash.com/400x300/?springroll',
    },
    {
      name: 'Paneer Tikka',
      description: 'Grilled paneer cubes with spicy marinade.',
      price: 220,
      image: 'https://source.unsplash.com/400x300/?paneer',
    },
  ];

  const postPerPage = 6;
  const [currentPage, setCurrentPage] = useState(1);
  const lastItemIndex = currentPage * postPerPage;
  const firstItemIndex = lastItemIndex - postPerPage;
  const selectedItems = items.slice(firstItemIndex, lastItemIndex);

  return (
    <>
      <Navbar links={links} />

      <div className="pt-4 px-10 bg-amber-50 min-h-screen">
        <h2 className="text-2xl font-bold mb-4 text-red-900">Our Menu</h2>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {selectedItems.map((item, index) => (
            <MenuCard key={index} item={item} />
          ))}
        </div>

        <div className="flex justify-center mt-6 space-x-2">
          {Array.from({ length: Math.ceil(items.length / postPerPage) }).map((_, index) => (
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
      </div>

      <Footer />
    </>
  );
}

export default Home;
