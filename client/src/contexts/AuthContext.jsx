// contexts/AuthContext.js
import React, { createContext, useContext, useState } from 'react';
import { toast } from 'react-toastify';
import { loginRestaurantOwner } from '../services/restaurantOwnerService';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = async (email, password, role) => {
    try {
      let loggedInUser = null;

      // ✅ Backend login only for 'restaurant' role
      if (role === 'restaurant') {
        loggedInUser = await loginRestaurantOwner(email, password);
      } else if (role === 'customer' && email === 'test@gmail.com' && password === 'test') {
        loggedInUser = { name: 'Test User', email, role: 'customer' };
      } else if (role === 'delivery' && email === 'del@gmail.com' && password === 'del') {
        loggedInUser = { name: 'Delivery Guy', email, role: 'delivery' };
      }

      if (loggedInUser) {
        setUser(loggedInUser);
        return loggedInUser;
      } else {
        return null;
      }
    } catch (err) {
      toast.error(err.message || 'Login failed');
      return null;
    }
  };

  const logout = () => {
    const name = user?.name;
    setUser(null);
    toast.success(name ? `${name} logged out successfully` : 'Logged out successfully');
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
