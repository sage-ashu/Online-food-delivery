// src/pages/Menu.jsx
import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import MenuItemCard from "../components/MenuItemCard";
import { getAllDishes } from "../services/dishService";

const Menu = () => {
  const [dishes, setDishes] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);

  const itemsPerPage = 6;

  useEffect(() => {
    const fetchDishes = async () => {
      try {
        const res = await getAllDishes();
        if (res.success) {
          setDishes(res.data);
        } else {
          console.error(res.message);
        }
      } catch (err) {
        console.error("Failed to fetch dishes", err);
      }
    };

    fetchDishes();
  }, []);

  const totalPages = Math.ceil(dishes.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentItems = dishes.slice(startIndex, startIndex + itemsPerPage);

  const handleChangePage = (page) => {
    setCurrentPage(page);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-orange-50 to-white">
      <Navbar />

      <section className="py-16 px-4 sm:px-8">
        <h1 className="text-4xl font-bold text-orange-700 text-center mb-6">
          Explore Our Menu 🍽️
        </h1>
        <p className="text-center text-gray-600 mb-10">
          Handpicked dishes just for you!
        </p>

        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8 max-w-6xl mx-auto">
          {currentItems.map((item) => (
            <MenuItemCard key={item.id} item={item} />
          ))}
        </div>

        {/* Pagination */}
        <div className="mt-12 flex justify-center gap-3">
          {Array.from({ length: totalPages }, (_, index) => (
            <button
              key={index + 1}
              onClick={() => handleChangePage(index + 1)}
              className={`px-4 py-2 rounded-full transition duration-300 ${
                currentPage === index + 1
                  ? "bg-orange-600 text-white shadow"
                  : "bg-orange-100 text-orange-700 hover:bg-orange-200"
              }`}
            >
              {index + 1}
            </button>
          ))}
        </div>

        <div className="mt-20 py-12 px-6 bg-gradient-to-tr from-orange-100 to-white text-center rounded-xl shadow-inner animate-fadeInSlow">
          <h3 className="text-2xl font-bold text-orange-700 mb-4">
            Food that keeps you coming back!
          </h3>
          <p className="text-gray-700 max-w-2xl mx-auto">
            We’re committed to delivering delicious, fresh, and healthy meals
            made with the best ingredients. Every bite counts — because you
            deserve nothing but the best.
          </p>
        </div>
      </section>
    </div>
  );
};

export default Menu;
  