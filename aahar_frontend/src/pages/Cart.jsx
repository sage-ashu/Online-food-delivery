import React from 'react'
import Navbar from '../components/Navbar'


function Cart() {
    const navLinks = [
        { label: "Home", path: "/" },
        { label: "Cart", path: "/cart" },
        { label: "Login", path: "/login" },
        { label: "Register", path: "/register" },
        { label: "About Us", path: "/about" },
        { label: "Contact Us", path: "/contact" },
    ]
  return (
    <>
    <Navbar links={navLinks}></Navbar>
    <div>Cart
    </div>

    </>
  )
}

export default Cart