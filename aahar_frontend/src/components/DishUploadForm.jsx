import React, { useState } from "react";
import { addDish } from "../services/dishService";
import { toast } from "react-toastify";

function DishUploadForm({ restaurantId, onDishAdded }) {
    const [formData, setFormData] = useState({
      dishName: "",
      dishPrice: "",
      description: "",
      isVeg: false,
      preperationTime: "",
      noOfServings: "",
      isAvailable: true,
      image: null,
    });
  
    const handleChange = (e) => {
      const { name, value, type, checked, files } = e.target;
      if (type === "checkbox") {
        setFormData({ ...formData, [name]: checked });
      } else if (type === "file") {
        setFormData({ ...formData, image: files[0] });
      } else {
        setFormData({ ...formData, [name]: value });
      }
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
  
      try {
        await addDish(restaurantId, formData); // ✅ using service here
        toast.success("Dish added successfully!");
        onDishAdded();
      } catch (err) {
        console.error(err);
        toast.error("Error adding dish.");
      }
    };

  return (
    <div className="max-w-2xl mx-auto mt-6 bg-white shadow-xl rounded-lg p-6">
      <h2 className="text-2xl font-bold mb-4 text-center text-green-700">Add New Dish</h2>
      <form onSubmit={handleSubmit} className="grid grid-cols-1 gap-4">
        <input
          type="text"
          name="dishName"
          placeholder="Dish Name"
          onChange={handleChange}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none focus:ring-2 focus:ring-green-500"
        />

        <input
          type="number"
          name="dishPrice"
          placeholder="Dish Price (₹)"
          onChange={handleChange}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none focus:ring-2 focus:ring-green-500"
        />

        <textarea
          name="description"
          placeholder="Description"
          onChange={handleChange}
          required
          rows="3"
          className="border border-gray-300 p-2 rounded focus:outline-none focus:ring-2 focus:ring-green-500"
        />

        <input
          type="number"
          name="preperationTime"
          placeholder="Preparation Time (mins)"
          onChange={handleChange}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none focus:ring-2 focus:ring-green-500"
        />

        <input
          type="number"
          name="noOfServings"
          placeholder="No. of Servings"
          onChange={handleChange}
          required
          className="border border-gray-300 p-2 rounded focus:outline-none focus:ring-2 focus:ring-green-500"
        />

        <div className="flex items-center gap-3">
          <label className="flex items-center gap-2">
            <input
              type="checkbox"
              name="isVeg"
              checked={formData.isVeg}
              onChange={handleChange}
              className="accent-green-600"
            />
            <span>Vegetarian</span>
          </label>

          <label className="flex items-center gap-2">
            <input
              type="checkbox"
              name="isAvailable"
              checked={formData.isAvailable}
              onChange={handleChange}
              className="accent-green-600"
            />
            <span>Available</span>
          </label>
        </div>

        <div>
          <label className="block mb-1 font-medium">Dish Image</label>
          <input
            type="file"
            name="image"
            accept="image/*"
            onChange={handleChange}
            className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4
              file:rounded-full file:border-0 file:text-sm file:font-semibold
              file:bg-green-50 file:text-green-700 hover:file:bg-green-100"
          />
        </div>

        <button
          type="submit"
          className="bg-green-600 text-white py-2 px-6 rounded-lg hover:bg-green-700 transition-colors duration-300"
        >
          Add Dish
        </button>
      </form>
    </div>
  );
}

export default DishUploadForm;
