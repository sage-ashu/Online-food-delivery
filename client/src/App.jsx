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

        </Routes>
    </BrowserRouter>
    <ToastContainer></ToastContainer>
    </>
    
  );
}

export default App