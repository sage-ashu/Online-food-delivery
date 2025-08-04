// src/components/DishCard.jsx
import React from "react";

const BASE_IMAGE_URL = "http://localhost:9090/images/dishes/";

function DishCard({ dish, onRemove }) {
  const imageFileName = dish.imagePath?.split("\\").pop(); // Handles Windows-style path
  return (
    <div className="border rounded-lg shadow p-4 flex flex-col gap-2">
      {imageFileName && (
        <img
          src={`${BASE_IMAGE_URL}${imageFileName}`}
          alt={dish.dishName}
          className="h-40 w-full object-cover rounded-md"
          onError={(e) => {
            e.target.onerror = null;
            e.target.src = "/placeholder.jpg"; // fallback
          }}
        />
      )}
      <h3 className="text-lg font-bold">{dish.dishName}</h3>
      <p className="text-sm text-gray-600">{dish.description}</p>
      <p className="text-sm">₹{dish.dishPrice}</p>
      <p className="text-sm">Prep Time: {dish.preperationTime} mins</p>
      <p className="text-sm">Servings: {dish.noOfServings}</p>
      <p
        className={`text-sm font-semibold ${
          dish.available ? "text-green-600" : "text-red-500"
        }`}
      >
        {dish.available ? "In Stock" : "Out of Stock"}
      </p>

      <div className="flex justify-between mt-2">
        <button onClick={onRemove} className="text-red-500 hover:underline">
          Remove
        </button>
      </div>
    </div>
  );
}

export default DishCard;
