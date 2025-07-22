import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

function Register() {
  const [role, setRole] = useState('customer');
  const [restaurantType, setRestaurantType] = useState('pure_veg');
  const [name, setName] = useState('');
  const [area, setArea] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [vehicle, setVehicle] = useState('bike'); // For delivery partner

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!email || !password || !name || (role === 'restaurant' && !area)) {
      toast.error('⚠️ Please fill all required fields.');
      return;
    }

    const data = {
      role,
      name,
      email,
      password,
      ...(role === 'restaurant' && { area, restaurantType }),
      ...(role === 'delivery' && { area, vehicle }),
    };

    // Simulated registration
    try {
      console.log('Registered user:', data);
      navigate('/login');
    } catch (err) {
      toast.error('❌ Something went wrong during registration');
    }
  };

  return (
    <>
      {/* Back to Home Button */}
      <div className="absolute top-4 left-4">
        <Link
          to="/"
          className="bg-amber-900 text-white px-4 py-2 rounded-full shadow hover:bg-amber-800 transition duration-200"
        >
          ⬅ Back to Home
        </Link>
      </div>

      <div className="min-h-screen bg-amber-50 flex items-center justify-center p-6">
        <div className="bg-white shadow-2xl rounded-2xl p-8 max-w-md w-full">
          <h2 className="text-3xl font-bold text-center text-amber-900 mb-6">
            Join Aahar 🍲
          </h2>

          {/* Role Toggle */}
          <div className="flex justify-center mb-6 space-x-2">
            {['customer', 'restaurant', 'delivery'].map((r) => (
              <button
                key={r}
                className={`px-4 py-2 rounded-full border border-amber-900 ${
                  role === r ? 'bg-amber-900 text-white' : 'bg-white text-amber-900'
                }`}
                onClick={() => setRole(r)}
              >
                {r.charAt(0).toUpperCase() + r.slice(1)}
              </button>
            ))}
          </div>

          {/* Form */}
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block mb-1 text-sm font-semibold text-gray-700">Name</label>
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Enter your name"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            {(role === 'restaurant' || role === 'delivery') && (
              <div className="mb-4">
                <label className="block mb-1 text-sm font-semibold text-gray-700">Area</label>
                <input
                  type="text"
                  value={area}
                  onChange={(e) => setArea(e.target.value)}
                  placeholder="Location / Area"
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                  required
                />
              </div>
            )}

            {role === 'restaurant' && (
              <div className="mb-4">
                <label className="block mb-1 text-sm font-semibold text-gray-700">Veg Type</label>
                <select
                  value={restaurantType}
                  onChange={(e) => setRestaurantType(e.target.value)}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                >
                  <option value="pure_veg">Pure Veg</option>
                  <option value="hybrid">Hybrid</option>
                </select>
              </div>
            )}

            {role === 'delivery' && (
              <div className="mb-4">
                <label className="block mb-1 text-sm font-semibold text-gray-700">Vehicle</label>
                <select
                  value={vehicle}
                  onChange={(e) => setVehicle(e.target.value)}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                >
                  <option value="bike">Bike</option>
                  <option value="scooter">Scooter</option>
                  <option value="bicycle">Bicycle</option>
                </select>
              </div>
            )}

            <div className="mb-4">
              <label className="block mb-1 text-sm font-semibold text-gray-700">Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="you@example.com"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            <div className="mb-6">
              <label className="block mb-1 text-sm font-semibold text-gray-700">Password</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-amber-600"
                required
              />
            </div>

            <button
              type="submit"
              className="w-full bg-amber-900 hover:bg-amber-800 text-white py-2 rounded-lg transition duration-200"
            >
              Register as {role.charAt(0).toUpperCase() + role.slice(1)}
            </button>
          </form>

          <p className="mt-4 text-center text-sm text-gray-700">
            Already have an account?{' '}
            <Link to="/login" className="text-amber-900 font-semibold hover:underline">
              Login here
            </Link>
          </p>
        </div>
      </div>
    </>
  );
}

export default Register;
