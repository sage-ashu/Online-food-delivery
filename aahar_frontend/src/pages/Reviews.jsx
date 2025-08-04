import React from "react";
import Navbar from "../components/Navbar";

function Reviews() {
  return (
    <>
      <Navbar />
      <div className="p-4">
        <h1 className="text-2xl font-bold text-gray-800">Reviews Page</h1>
        <p className="text-gray-600 mt-2">This is where you can view and manage customer reviews.</p>
      </div>
    </>
  );
}

export default Reviews;
