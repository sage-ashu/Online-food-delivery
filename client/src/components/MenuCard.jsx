import React, { useState } from 'react';
import { useAuth } from '../contexts/AuthContext';
import { useCart } from '../contexts/CartContext'; // 👈 import cart context
import { toast } from 'react-toastify';

function MenuCard({ item }) {
  const [quantity, setQuantity] = useState(1);
  const { user } = useAuth();
  const { addToCart } = useCart(); // 👈 use cart context

  const handleAddToCart = () => {
    if (!user) {
      toast.error("Please login first");
      return;
    }

    addToCart({
      ...item,
      quantity,
    });

    toast.success(`Added ${quantity} x ${item.name} to cart`);
  };

  return (
    <div className="bg-white rounded-lg shadow-md overflow-hidden flex">
      {/* Left - Image */}
      <div className="w-1/2">
        <img
          src={item.image}
          alt={item.name}
          className="object-cover h-full w-full"
        />
      </div>

      {/* Right - Details */}
      <div className="w-1/2 p-4 flex flex-col justify-between">
        <div>
          <h3 className="text-xl font-bold text-red-900">{item.name}</h3>
          <p className="text-gray-600 text-sm mt-1">{item.description}</p>
          <p className="text-lg font-semibold mt-2 text-green-700">₹{item.price}</p>
        </div>
        <div className="flex items-center justify-between mt-4">
          <input
            type="number"
            value={quantity}
            min="1"
            onChange={(e) => setQuantity(Number(e.target.value))}
            className="w-16 p-1 border rounded text-center"
          />
          <button
            onClick={handleAddToCart}
            className="bg-red-900 text-white px-4 py-1 rounded hover:bg-red-800"
          >
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
}

export default MenuCard;
