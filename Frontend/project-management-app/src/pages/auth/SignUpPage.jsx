import React, { useState } from "react";
import axios from "axios";
import SignUp from "../../components/Auth/SignUp";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function SignUpPage({ onSignUpSuccess }) {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // We use onSignUpSuccess to redirect to the login page
  const handleLoginClick = () => {
    onSignUpSuccess();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      toast.error('Passwords do not match!', {
        position: "top-center",
        autoClose: 3000,
      });
      setErrorMessage("Passwords do not match!");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/v1/users", {
        fullName: formData.fullName,
        email: formData.email,
        password: formData.password,
      });

      setSuccessMessage("Sign-up successful! You can now log in.");
      setErrorMessage("");
      
      toast.success('Sign-up successful! Redirecting to login...', {
        position: "top-center",
        autoClose: 2000,
        onClose: () => {
          setTimeout(() => {
            onSignUpSuccess(); // Redirect to login page after successful registration
          }, 500);
        }
      });

      console.log("Response from backend:", response.data);
    } catch (error) {
      const errorMsg = error.response?.data?.message || "An error occurred during sign-up!";
      setErrorMessage(errorMsg);
      
      toast.error(`❌ ${errorMsg}`, {
        position: "top-center",
        autoClose: 3000,
      });
      
      console.error("Error during sign-up:", error);
    }
  };

  return (
    <main>
      <SignUp
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        errorMessage={errorMessage}
        successMessage={successMessage}
        handleLoginClick={handleLoginClick}
      />
      <ToastContainer 
        position="top-center"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable={false}
        pauseOnHover
        theme="colored"
      />
    </main>
  );
}