// src/context/AuthContext.js
import { createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [authUser, setAuthUser] = useState(null);

  const login = (email, password, role) => {
    // Dummy hardcoded data
    const dummyUsers = [
      {
        email: "customer@example.com",
        password: "123456",
        role: "customer",
      },
      {
        email: "restaurant@example.com",
        password: "abcdef",
        role: "restaurant",
      },
    ];

    const foundUser = dummyUsers.find(
      (user) =>
        user.email === email &&
        user.password === password &&
        user.role === role
    );

    if (foundUser) {
      setAuthUser(foundUser);
      return { success: true };
    } else {
      return { success: false, message: "Invalid credentials or role" };
    }
  };

  const logout = () => {
    setAuthUser(null);
  };

  return (
    <AuthContext.Provider value={{ authUser, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook
export const useAuth = () => useContext(AuthContext);
