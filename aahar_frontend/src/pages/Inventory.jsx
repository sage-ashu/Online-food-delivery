// src/pages/Inventory.jsx
import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import DishUploadForm from "../components/DishUploadForm";
import DishCard from "../components/DishCard";
import {
  addDish,
  getDishesByRestaurant,
  deleteDishById,
} from "../services/dishService";

function Inventory() {
  const restaurantId = 1; // 🔒 Replace with actual dynamic ID from logged-in user
  const [dishes, setDishes] = useState([]);

  // ✅ Fetch dishes
  const fetchDishes = async () => {
    try {
      const res = await getDishesByRestaurant(restaurantId);
      setDishes(res); // backend returns DTO list
    } catch (err) {
      console.error("Error fetching dishes", err);
    }
  };

  // ✅ Fetch on load
  useEffect(() => {
    fetchDishes();
  }, []);

  // ✅ Delete handler
  const removeDish = async (dishId) => {
    try {
      await deleteDishById(restaurantId, dishId);
      fetchDishes();
    } catch (err) {
      console.error("Failed to delete dish", err);
    }
  };

  return (
    <>
      <Navbar />
      <div className="p-6 grid grid-cols-1 md:grid-cols-2 gap-6">
        {/* ✅ Left Side: Form */}
        <div>
          <h2 className="text-xl font-semibold mb-4">Add New Dish</h2>
          <DishUploadForm
            restaurantId={restaurantId}
            onDishAdded={fetchDishes}
          />
        </div>

        {/* ✅ Right Side: Dish List */}
        <div>
          <h2 className="text-xl font-semibold mb-4">Your Dishes</h2>
          <div className="grid grid-cols-1 gap-4">
            {dishes.length === 0 ? (
              <p className="text-gray-500 italic">No dishes added yet.</p>
            ) : (
              dishes.map((dish) => (
                <DishCard
                  key={dish.id}
                  dish={dish}
                  onRemove={() => removeDish(dish.id)}
                />
              ))
            )}
          </div>
        </div>
      </div>
    </>
  );
}

export default Inventory;
