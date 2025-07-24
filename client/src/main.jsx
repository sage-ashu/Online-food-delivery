import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { AuthProvider } from './contexts/AuthContext';
import { CartProvider } from './contexts/CartContext';
import { MenuProvider } from './contexts/MenuContext';

ReactDOM.createRoot(document.getElementById('root')).render(
  <MenuProvider>
  <CartProvider>
  <AuthProvider>
    <App />
  </AuthProvider>
  </CartProvider>
  </MenuProvider>
);
