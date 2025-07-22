import React, { createContext, useContext, useState } from 'react';
import { toast } from 'react-toastify';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = (email, password) => {

// In this case the backend is hit with user login credentials 

//Temporarily hardcoded values
    if (email === 'test@gmail.com' && password === 'test') {
      setUser({ name: 'Test User', email });
      return true;
    }
    return false;
  };

  const logout = () => {
    setUser(null);
    toast.success(`${user.name} logged out successfully`);

  }

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
