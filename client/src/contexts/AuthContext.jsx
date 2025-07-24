import React, { createContext, useContext, useState } from 'react';
import { toast } from 'react-toastify';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = (email, password) => {
    // Replace this mock login with backend call if needed
    if (email === 'test@gmail.com' && password === 'test') {
      const loggedInUser = { name: 'Test User', email, role: 'customer' };
      setUser(loggedInUser);
      return loggedInUser;
    } else if (email === 'rest@gmail.com' && password === 'rest') {
      const loggedInUser = { name: 'Rest User', email, role: 'restaurant' };
      setUser(loggedInUser);
      return loggedInUser;
    } else if (email === 'del@gmail.com' && password === 'del') {
      const loggedInUser = { name: 'Delivery Guy', email, role: 'delivery' };
      setUser(loggedInUser);
      return loggedInUser;
    }
    return null;
  };

  const logout = () => {
    toast.success(`${user?.name} logged out successfully`);
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
