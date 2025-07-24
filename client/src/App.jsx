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
import R_RestaurantDashboard from './pages/R_Restaurant_Dashboard';
import R_Restaurant_Account from './pages/R_Restaurant_Account';
import D_Delivery_Dashboard from './pages/D_Delivery_Dashboard';
import R_Manage_Menu from './pages/R_Manage_Menu';
import R_Manage_Orders from './pages/R_Manage_Orders';
import R_Restaurant_History from './pages/R_Restaurant_History';
import R_Contact_us from './pages/R_Contact_us';


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
        <Route path="/restaurant" element={<R_RestaurantDashboard/>} />
        <Route path="/restaurant/account" element={<R_Restaurant_Account/>} />
        <Route path="/restaurant/menu" element={<R_Manage_Menu/>} />
        <Route path="/restaurant/orders" element={<R_Manage_Orders/>} />
        <Route path="/restaurant/history" element={<R_Restaurant_History/>} />
        <Route path="/restaurant/contact" element={<R_Contact_us/>} />
        <Route path="/delivery" element={<D_Delivery_Dashboard/>} />



        </Routes>
    </BrowserRouter>
    <ToastContainer></ToastContainer>
    </>
    
  );
}

export default App