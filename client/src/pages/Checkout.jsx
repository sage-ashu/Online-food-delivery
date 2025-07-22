// pages/Checkout.jsx
import React, { useEffect, useState } from 'react';
import { useCart } from '../contexts/CartContext';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import { useAuth } from '../contexts/AuthContext';

function Checkout() {
  const { cartItems, clearCart } = useCart();
  const [orderItems, setOrderItems] = useState([]);
  const [orderPlaced, setOrderPlaced] = useState(false);
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const navLinks = [
    { name: user.name, path: "/account" },
    { name: "Home", path: "/" },
    { name: "Cart", path: "/cart" },
    { name: "Contact Us", path: "/contact" },
    { name: "Logout", path: "#", onClick: logout },
  ];

  useEffect(() => {
    // Use context data, fallback to localStorage
    if (cartItems.length > 0) {
      setOrderItems(cartItems);
    } else {
      const stored = localStorage.getItem("cartItems");
      if (stored) {
        setOrderItems(JSON.parse(stored));
      }
    }
  }, [cartItems]);

  const totalPrice = orderItems.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const handlePlaceOrder = () => {
    setOrderPlaced(true);
    clearCart(); // clear context
    localStorage.removeItem("cartItems"); // clear localStorage too
    setTimeout(() => {
      navigate("/"); // navigate home after few seconds
    }, 3000);
  };

  return (
    <>
      <Navbar links={navLinks} />

      <div className="p-6 bg-green-50 min-h-screen">
        <h2 className="text-2xl font-bold text-green-800 mb-4">Checkout</h2>

        {orderPlaced ? (
          <div className="bg-white p-6 shadow rounded text-center">
            <h3 className="text-xl font-semibold text-green-700 mb-2">✅ Order Placed!</h3>
            <p className="text-gray-700">Your order will be delivered shortly.</p>
          </div>
        ) : orderItems.length === 0 ? (
          <p>No items to checkout.</p>
        ) : (
          <>
            <div className="space-y-3">
              {orderItems.map((item, index) => (
                <div
                  key={index}
                  className="flex justify-between bg-white p-4 rounded shadow"
                >
                  <div>
                    <h4 className="font-bold">{item.name}</h4>
                    <p>
                      ₹{item.price} × {item.quantity} = ₹{item.price * item.quantity}
                    </p>
                  </div>
                </div>
              ))}
            </div>

            <div className="text-right mt-4 font-semibold text-lg text-green-900">
              Total: ₹{totalPrice}
            </div>

            <div className="mt-6 text-center">
              <button
                onClick={handlePlaceOrder}
                className="bg-green-600 text-white px-6 py-2 rounded hover:bg-green-500 transition duration-200"
              >
                ✅ Place Order
              </button>
            </div>
          </>
        )}
      </div>
    </>
  );
}

export default Checkout;
