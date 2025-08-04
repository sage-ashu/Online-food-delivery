import React from "react";
import Navbar from "../components/Navbar";

function Orders() {
  return (
    <>
      <Navbar />
      <div className="p-4">
        <h1 className="text-2xl font-bold text-gray-800">Orders Page</h1>
        <p className="text-gray-600 mt-2">This is where orders will be displayed.</p>
      </div>
    </>
  );
}

export default Orders;
