import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { toast } from 'react-toastify';

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    const loggedInUser = login(email, password);

    if (loggedInUser) {
      toast.success(`${loggedInUser.name} logged in successfully`);

      // Navigate based on role
      switch (loggedInUser.role) {
        case 'customer':
          navigate('/');
          break;
        case 'restaurant':
          navigate('/restaurant');
          break;
        case 'delivery':
          navigate('/delivery');
          break;
        default:
          navigate('/');
      }
    } else {
      toast.error("Invalid credentials.😅");
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
            Welcome back to Aahar 🍽️
          </h2>

          {/* Form */}
          <form onSubmit={handleSubmit}>
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
              Login
            </button>
          </form>

          {/* Register Link */}
          <p className="mt-4 text-center text-sm text-gray-700">
            New to Aahar?{' '}
            <Link to="/register" className="text-amber-900 font-semibold hover:underline">
              Register here
            </Link>
          </p>
        </div>
      </div>
    </>
  );
}

export default Login;
