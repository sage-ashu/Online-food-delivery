import React from 'react';

function Button({ label }) {
  return (
    <button className="text-white   mx-4 px-4  py-2 rounded hover:bg-white hover:text-black transition duration-300">
      {label}
    </button>
  );
}

export default Button;
