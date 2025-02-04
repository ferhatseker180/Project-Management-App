import React from "react";

export default function SignIn({ onLogin, onSignUp, handleChange }) {
  return (
    <div className="flex justify-center items-center min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 px-4 py-8">
      <div className="bg-white shadow-xl rounded-2xl p-4 sm:p-8 w-full max-w-md border border-gray-100">
        <h2 className="text-2xl sm:text-3xl font-bold text-gray-800 text-center mb-6 sm:mb-8 bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
          Login
        </h2>
        <form onSubmit={onLogin} className="space-y-4 sm:space-y-6">
          <div>
            <label htmlFor="email" className="block text-gray-700 font-medium mb-1.5 sm:mb-2 text-sm">
              Email Address
            </label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Enter your email"
              required
              className="w-full px-3 sm:px-4 py-2.5 sm:py-3 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 bg-gray-50 hover:bg-gray-100"
              onChange={handleChange}
            />
          </div>
          <div>
            <label htmlFor="password" className="block text-gray-700 font-medium mb-1.5 sm:mb-2 text-sm">
              Password
            </label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter your password"
              required
              className="w-full px-3 sm:px-4 py-2.5 sm:py-3 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 bg-gray-50 hover:bg-gray-100"
              onChange={handleChange}
            />
          </div>
          <button
            type="submit"
            className="w-full bg-gradient-to-r from-blue-600 to-indigo-600 text-white font-semibold py-2.5 sm:py-3 px-4 sm:px-6 rounded-xl transition duration-300 transform hover:scale-[1.02] hover:shadow-lg mt-6"
          >
            Login
          </button>
        </form>
        <p className="text-sm text-gray-600 text-center mt-4 sm:mt-6">
          Don't have an account?{" "}
          <button onClick={onSignUp} className="text-blue-600 hover:text-indigo-600 font-medium transition-colors duration-200">
            Sign up
          </button>
        </p>
      </div>
    </div>
  );
}