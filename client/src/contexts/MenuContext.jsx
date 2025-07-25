// contexts/MenuContext.js
import React, { createContext, useContext, useEffect, useState } from "react";

const MenuContext = createContext();

export function MenuProvider({ children }) {
  const [menuItems, setMenuItems] = useState(() => {
    const stored = localStorage.getItem("menuItems");
    return stored ? JSON.parse(stored) : [];
  });

  useEffect(() => {
    localStorage.setItem("menuItems", JSON.stringify(menuItems));
  }, [menuItems]);

  const addMenuItem = (item) => {
    setMenuItems((prev) => [...prev, item]);
  };

  const removeMenuItem = (index) => {
    setMenuItems((prev) => prev.filter((_, i) => i !== index));
  };

  const updateMenuItem = (index, updatedItem) => {
    setMenuItems((prev) =>
      prev.map((item, i) => (i === index ? { ...item, ...updatedItem } : item))
    );
  };

  const clearMenu = () => {
    setMenuItems([]);
  };

  return (
    <MenuContext.Provider
      value={{ menuItems, addMenuItem, removeMenuItem, updateMenuItem, clearMenu }}
    >
      {children}
    </MenuContext.Provider>
  );
}

export function useMenu() {
  return useContext(MenuContext);
}
