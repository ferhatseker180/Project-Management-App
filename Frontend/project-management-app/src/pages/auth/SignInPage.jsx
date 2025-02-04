import React, { useState, useEffect } from "react";
import axios from "axios";
import SignIn from "../../components/Auth/SignIn";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function SignInPage({ setCurrentPage }) {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.interceptors.request.use(
      (config) => {
        const token = localStorage.getItem('token');
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      const response = await axios.post("http://localhost:8080/v1/users/login", formData, {
        headers: { "Content-Type": "application/json" },
      });

      const { id, token } = response.data.data;
      localStorage.setItem("token", token);
      localStorage.setItem("userId", id);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

      // Successful Login toast
      toast.success('🎉 Login successful!', {
        position: "top-center",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored",
        onClose: () => setCurrentPage("main") // Redirect after Toast is closed
      });
    } catch (err) {
      console.error("Login Error:", err.response?.data || err.message);
      setError(err.response?.data?.message || "Login failed! Please try again.");
      
      // Hata toast'u
      toast.error('❌ Login failed! Please check your credentials.', {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored"
      });
    }
  };

  return (
    <div>
      <SignIn 
        onLogin={handleSubmit} 
        onSignUp={() => setCurrentPage("signUp")} 
        handleChange={handleChange} 
      />
      <ToastContainer
        position="top-center"
        autoClose={3000}
        limit={1}
        hideProgressBar={false}
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable={false}
        pauseOnHover={true}
        theme="colored"
      />
    </div>
  );
}