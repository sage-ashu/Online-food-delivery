import React from 'react';

function Footer() {
  return (
    <footer className="w-full h-40 bg-red-900 text-white flex flex-col items-center justify-center text-center px-4">
      <h2 className="text-xl font-semibold">Aahar 🍽️</h2>
      <p className="mt-2 text-sm italic">
        Powered by Biryani,Biryani, and Biryani.
      </p>
      <p className="mt-1 text-sm">
        If you're still hungry, it's not our fault — refresh the page!
      </p>
      <p className="mt-1 text-xs text-gray-400">
        © {new Date().getFullYear()} Aahar Inc. — Homely Meals!
      </p>
    </footer>
  );
}

export default Footer;
