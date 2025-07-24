import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Contact from './pages/Contact';
import { ToastContainer} from 'react-toastify';
import Cart from './pages/Cart';
import Account from './pages/Account';
import Checkout from './pages/Checkout';
import Restaurant_Dashboard from './pages/Restaurant_Dashboard';
import Delivery_Dashboard from './pages/Delivery_Dashboard';


function App() {
  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home></Home>}/>
        <Route path='/login' element={<Login></Login>}/>
        <Route path='/register' element={<Register></Register>}/>
        <Route path='/cart' element={<Cart></Cart>}/>
        <Route path='/contact' element={<Contact></Contact>}/>
        <Route path='/account' element={<Account></Account>}/>
        <Route path="/checkout" element={<Checkout/>} />
        <Route path="/restaurant" element={<Restaurant_Dashboard/>} />
        <Route path="/delivery" element={<Delivery_Dashboard/>} />


        </Routes>
    </BrowserRouter>
    <ToastContainer></ToastContainer>
    </>
    
  );
}

export default App