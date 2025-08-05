// src/components/Navbar.jsx
import React from "react";
import { NavLink } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const Navbar = () => {
  const { authUser, logout } = useAuth();

  const renderLinks = () => {
    if (!authUser) {
      return [
        { label: "Home", path: "/" },
        { label: "Login", path: "/login" },
        { label: "Register", path: "/register" },
        { label: "About Us", path: "/about" },
        { label: "Contact", path: "/contact" },
      ];
    }

    if (authUser.role === "customer") {
      return [
        { label: "Home", path: "/" },
        { label: "Menu", path: "/menu" },
        { label: "Cart", path: "/cart" },
        { label: "Orders", path: "/customer-orders" },
        { label: "Profile", path: "/profile" },
        { label: "About Us", path: "/about" },
        { label: "Logout", path: "/", action: logout },
      ];
    }

    if (authUser.role === "restaurant") {
      return [
        { label: "Orders", path: "/orders" },
        { label: "Inventory", path: "/inventory" },
        { label: "Profile", path: "/restaurant-info" },
        { label: "Reviews", path: "/reviews" },
        { label: "Logout", path: "/", action: logout },
      ];
    }

    return [];
  };

  return (
    <nav className="bg-orange-700 text-white shadow-md p-4">
      <div className="container mx-auto flex justify-between items-center">
        <div className="text-xl font-bold">Aahar</div>
        <div className="flex gap-4">
          {renderLinks().map((link) =>
            link.action ? (
              <button
                key={link.label}
                onClick={link.action}
                className="hover:text-yellow-200"
              >
                {link.label}
              </button>
            ) : (
              <NavLink
                key={link.path}
                to={link.path}
                className={({ isActive }) =>
                  isActive ? "text-yellow-300" : "hover:text-yellow-200"
                }
              >
                {link.label}
              </NavLink>
            )
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
