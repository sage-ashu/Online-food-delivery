import React, { useState } from "react";
import Navbar from "../components/Navbar";
import { useAuth } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import { useMenu } from "../contexts/MenuContext";
 // 👈 import it

function R_Manage_Menu() {
  const navigate = useNavigate();
  const { logout } = useAuth();
  const { menuItems, addMenuItem, removeMenuItem, updateMenuItem } = useMenu(); // 👈 use context

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: "",
    imageFile: null,
    imagePreview: null,
  });

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const links = [
    { name: "Home", path: "/restaurant" },
    { name: "Manage Menu", path: "/restaurant/menu" },
    { name: "Manage Orders", path: "/restaurant/orders" },
    { name: "Order History", path: "/restaurant/history" },
    { name: "Account", path: "/restaurant/account" },
    { name: "Contact Us", path: "/restaurant/contact" },
    { name: "Logout", onClick: handleLogout },
  ];

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "imageFile" && files.length > 0) {
      const file = files[0];
      const previewUrl = URL.createObjectURL(file);
      setFormData((prev) => ({
        ...prev,
        imageFile: file,
        imagePreview: previewUrl,
      }));
    } else {
      setFormData((prev) => ({ ...prev, [name]: value }));
    }
  };

  const handleAddItem = (e) => {
    e.preventDefault();

    if (!formData.imageFile) {
      console.log("No image selected");
      return;
    }

    const newItem = {
      name: formData.name,
      description: formData.description,
      price: Number(formData.price),
      imagePreview: formData.imagePreview,
      quantity: 1,
    };

    addMenuItem(newItem); // 👈 store via context

    // Reset form
    setFormData({
      name: "",
      description: "",
      price: "",
      imageFile: null,
      imagePreview: null,
    });
  };

  const handleIncrease = (index) => {
    const updated = { ...menuItems[index], quantity: menuItems[index].quantity + 1 };
    updateMenuItem(index, updated); // 👈 context update
  };

  const handleRemove = (index) => {
    removeMenuItem(index); // 👈 context remove
  };

  return (
    <>
      <Navbar links={links} />
      <div className="flex flex-col lg:flex-row gap-6 p-6 bg-amber-50 min-h-screen">
        {/* Left: Form */}
        <form
          onSubmit={handleAddItem}
          className="w-full lg:w-1/2 p-6 bg-white rounded-xl shadow-md border border-gray-200 space-y-4"
        >
          <h2 className="text-2xl font-bold text-red-800">Add Menu Item</h2>

          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Item Name"
            className="w-full p-2 border rounded"
            required
          />
          <textarea
            name="description"
            value={formData.description}
            onChange={handleChange}
            placeholder="Item Description"
            className="w-full p-2 border rounded"
            required
          />
          <input
            type="number"
            name="price"
            value={formData.price}
            onChange={handleChange}
            placeholder="Price (₹)"
            className="w-full p-2 border rounded"
            required
          />
          <input
            type="file"
            name="imageFile"
            accept="image/*"
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />

          {formData.imagePreview && (
            <img
              src={formData.imagePreview}
              alt="Preview"
              className="w-24 h-24 object-cover rounded"
            />
          )}

          <button
            type="submit"
            className="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700"
          >
            Add Item
          </button>
        </form>

        {/* Right: Menu Display */}
        <div className="w-full lg:w-1/2 space-y-4">
          <h2 className="text-2xl font-bold text-red-800">Menu Preview</h2>
          {menuItems.length === 0 ? (
            <p className="text-gray-500">No items added yet.</p>
          ) : (
            menuItems.map((item, index) => (
              <div
                key={index}
                className="flex gap-4 bg-white p-4 border rounded shadow-sm items-center"
              >
                <img
                  src={item.imagePreview}
                  alt={item.name}
                  className="w-24 h-24 object-cover rounded"
                />
                <div className="flex-1">
                  <h3 className="text-xl font-semibold">{item.name}</h3>
                  <p className="text-sm text-gray-600">{item.description}</p>
                  <p className="text-red-800 font-semibold">₹{item.price}</p>
                  <p className="text-sm text-gray-600">Quantity: {item.quantity}</p>
                </div>
                <div className="flex flex-col gap-2">
                  <button
                    onClick={() => handleIncrease(index)}
                    className="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600"
                  >
                    ➕
                  </button>
                  <button
                    onClick={() => handleRemove(index)}
                    className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
                  >
                    🗑️
                  </button>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
    </>
  );
}

export default R_Manage_Menu;
