// src/components/CartItemCard.jsx
import React from "react";
import { X } from "lucide-react";

const BASE_IMAGE_URL = "http://localhost:9090/images/dishes/";

const CartItemCard = ({ item, onRemove }) => {
  return (
    <div className="flex items-center justify-between gap-4 bg-white rounded-xl shadow-sm p-4 mb-4 hover:shadow-md transition">
      <div className="flex items-center gap-4">
        <img
          src={`${BASE_IMAGE_URL}${item.imagePath?.split("\\").pop()}`}
          alt={item.dishName}
          className="w-16 h-16 object-cover rounded-lg border"
          onError={(e) => {
            e.target.onerror = null;
            e.target.src = "/placeholder.jpg";
          }}
        />
        <div>
          <h4 className="text-lg font-semibold text-gray-800">{item.dishName}</h4>
          <p className="text-sm text-gray-500">Qty: {item.quantity}</p>
        </div>
      </div>

      <div className="flex items-center gap-4">
        <span className="text-orange-600 font-semibold text-md">
          ₹{item.dishPrice * item.quantity}
        </span>
        <button
          onClick={() => onRemove(item.id)}
          className="text-red-500 hover:text-red-700"
          title="Remove item"
        >
          <X size={20} />
        </button>
      </div>
    </div>
  );
};

export default CartItemCard;
