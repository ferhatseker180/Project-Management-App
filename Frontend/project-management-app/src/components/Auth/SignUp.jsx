// SignUp.jsx
import React from "react";

export default function SignUp({
  formData,
  handleChange,
  handleSubmit,
  errorMessage,
  successMessage,
  handleLoginClick
}) {
  return (
    <div className="flex justify-center items-center min-h-screen bg-gradient-to-br from-blue-100 to-blue-300 px-4">
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md">
        <h2 className="text-3xl font-extrabold text-gray-900 text-center mb-6">
          Create an Account
        </h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          {[
            { label: "Full Name", id: "fullName", type: "text" },
            { label: "Email Address", id: "email", type: "email" },
            { label: "Password", id: "password", type: "password" },
            { label: "Confirm Password", id: "confirmPassword", type: "password" },
          ].map(({ label, id, type }) => (
            <div key={id} className="relative">
              <label htmlFor={id} className="block text-gray-700 font-medium mb-1">
                {label}
              </label>
              <input
                type={type}
                id={id}
                name={id}
                placeholder={`Enter your ${label.toLowerCase()}`}
                required
                value={formData[id]}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400 transition duration-200"
              />
            </div>
          ))}

          {errorMessage && <p className="text-red-500 text-sm text-center">{errorMessage}</p>}
          {successMessage && <p className="text-green-500 text-sm text-center">{successMessage}</p>}

          <button
            type="submit"
            className="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 rounded-lg shadow-md transition duration-300 transform hover:scale-105"
          >
            Sign Up
          </button>
        </form>

        <p className="text-sm text-gray-600 text-center mt-4">
          Already have an account?{" "}
          <button
            onClick={handleLoginClick} 
            type="button"
            className="text-blue-500 hover:underline font-medium"
          >
            Login
          </button>
        </p>
      </div>
    </div>
  );
}