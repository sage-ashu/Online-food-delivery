import { createContext, useContext, useEffect, useState } from "react";
import { loginRestaurantOwner, loginCustomer } from "../services/loginService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { fetchCart } from "../services/cartService";
import { clearCartBackend } from "../services/cartService";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const [authUser, setAuthUser] = useState(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("authUser");
    if (storedUser) {
      setAuthUser(JSON.parse(storedUser));
    }
  }, []);

  const login = async (email, password, role) => {
    let result;
  
    if (role === "restaurant") {
      result = await loginRestaurantOwner(email, password);
    } else if (role === "customer") {
      result = await loginCustomer(email, password);
    } else {
      return { success: false, message: "Unknown role" };
    }
  
    if (result.success) {
      const userData = {
        ...result.data,
        role,
      };
  
      setAuthUser(userData);
      localStorage.setItem("authUser", JSON.stringify(userData));
  
      // ✅ NEW: fetch customer's cart from backend
      if (role === "customer") {
        const backendCart = await fetchCart(userData.id);
        if (backendCart) {
          const formattedCart = backendCart.items.map(item => ({
            id: item.dishId,
            dishName: item.dishName,
            dishPrice: item.dishPrice,
            quantity: item.quantity,
            restaurantId: backendCart.restaurantId,
          }));
  
          localStorage.setItem("cartItems", JSON.stringify(formattedCart));
          localStorage.setItem("restaurantId", backendCart.restaurantId || "");
        }
      }
  
      toast.success("Login successful");
      return { success: true, userData };
    } else {
      toast.error(result.message || "Login failed");
      return { success: false, message: result.message };
    }
  };


  const logout = async () => {
    const authUser = JSON.parse(localStorage.getItem("authUser"));
    if (authUser?.role === "customer") {
      await clearCartBackend(authUser.id); // ✅ backend clear
    }
  
    // ✅ clear cart local storage
    localStorage.removeItem("cartItems");
    localStorage.removeItem("restaurantId");
    localStorage.removeItem("authUser");
  
    setAuthUser(null);
    navigate("/login");
    toast.info("Successfully logged out");
  };
  

  return (
    <AuthContext.Provider value={{ authUser, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
