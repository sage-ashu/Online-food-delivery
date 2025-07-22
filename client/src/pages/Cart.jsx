import React from 'react';
import { useCart } from '../contexts/CartContext';
import Navbar from '../components/Navbar';
import { useAuth } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

function Cart() {
  const { cartItems, removeFromCart } = useCart();
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const navLinks = [
    { name: user.name, path: "/account" },
    { name: "Home", path: "/" },
    { name: "Cart", path: "/cart" },
    { name: "Contact Us", path: "/contact" },
    { name: "Logout", path: "#", onClick: logout },
  ];

  return (
    <>
      <Navbar links={navLinks} />
      <div className="p-6 bg-amber-50 min-h-screen">
        <h2 className="text-2xl font-bold text-red-900 mb-4">🛒 Your Cart</h2>

        {cartItems.length === 0 ? (
          <p>No items in cart.</p>
        ) : (
          <>
            {cartItems.map((item, index) => (
              <div
                key={index}
                className="flex justify-between items-center bg-white p-4 mb-2 shadow rounded"
              >
                <div>
                  <h3 className="font-bold">{item.name}</h3>
                  <p>
                    ₹{item.price} x {item.quantity} = ₹{item.price * item.quantity}
                  </p>
                </div>
                <button
                  onClick={() => removeFromCart(item.name)}
                  className="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-500"
                >
                  Remove
                </button>
              </div>
            ))}

            {/* Checkout Button */}
            <div className="mt-6 text-right">
              <button
                onClick={() => navigate("/checkout")}
                className="bg-green-600 text-white px-6 py-2 rounded hover:bg-green-500 transition duration-200"
              >
                Proceed to Checkout
              </button>
            </div>
          </>
        )}
      </div>
    </>
  );
}

export default Cart;
