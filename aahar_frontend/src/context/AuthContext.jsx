  // src/context/AuthContext.js
  import { createContext, useContext, useState } from "react";
  import { loginRestaurantOwner } from "../services/loginService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

  const AuthContext = createContext();

  export const AuthProvider = ({ children }) => {
    const [authUser, setAuthUser] = useState(null);
    const navigate = useNavigate();

    const login = async (email, password, role) => {
      if (role === "restaurant") {
        const result = await loginRestaurantOwner(email, password);

        if (result.success) {
          const userData = {
            ...result.data, // id, name, email, phoneNumber
            role: "restaurant",
          };
          setAuthUser(userData);
          return { success: true };
        } else {
          return { success: false, message: result.message };
        }
      }

      // You can still keep dummy logic for customer (optional)
      if (role === "customer") {
        const dummyCustomer = {
          email: "customer@example.com",
          password: "123456",
          role: "customer",
        };

        if (email === dummyCustomer.email && password === dummyCustomer.password) {
          setAuthUser(dummyCustomer);
          return { success: true };
        } else {
          return { success: false, message: "Invalid credentials" };
        }
      }

      return { success: false, message: "Unknown role" };
    };

    const logout = () => {
      setAuthUser(null);
      navigate("/login")
      toast.info("Successfully logged out ");
    };

    return (
      <AuthContext.Provider value={{ authUser, login, logout }}>
        {children}
      </AuthContext.Provider>
    );
  };

  export const useAuth = () => useContext(AuthContext);
