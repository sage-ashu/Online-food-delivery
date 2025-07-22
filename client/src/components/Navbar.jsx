import React from "react";
import { Link, useNavigate } from "react-router-dom";

function Navbar({ links }) {
  const navigate = useNavigate();

  return (
    <div className="w-full h-16 bg-red-900 text-white shadow-xl shadow-black/50 flex items-center justify-between px-6">
      {/* Left side - Logo */}
      <div className="font-bold text-2xl tracking-wide">Aahar 🍴</div>

      {/* Right side - Links */}
      <div className="flex space-x-3">
        {links.map((link, idx) => (
          <button
            key={idx}
            onClick={() =>
              link.onClick ? link.onClick() : navigate(link.path)
            }
            className="px-4 py-2 rounded-full bg-red-800 hover:bg-white hover:text-red-900 border border-red-700 transition-all duration-200 text-sm font-medium"
          >
            {link.name}
          </button>
        ))}
      </div>
    </div>
  );
}

export default Navbar;
