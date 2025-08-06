import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Home from './pages/Home'
import About from './pages/About'
import Contact from './pages/Contact'
import Login from './pages/Login'
import Register from './pages/Register'
import Cart from './pages/Cart'
import Menu from './pages/Menu'
import Orders from './pages/Orders'
import Inventory from './pages/Inventory'
import YourRestro from './pages/YourRestro'
import Reviews from './pages/Reviews'
import CustomerProfile from './pages/CustomerProfile'
import Checkout from './pages/Checkout'
import CustomerOrders from './pages/CustomerOrders'

function App() {
  return (
    <>
    <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/inventory" element={<Inventory />} />
        <Route path="/restaurant-info" element={<YourRestro />} />
        <Route path="/reviews" element={<Reviews />} />
        <Route path="/profile" element={<CustomerProfile />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/customer-orders" element={<CustomerOrders />} />
      </Routes>
    </>
  )
}

export default App