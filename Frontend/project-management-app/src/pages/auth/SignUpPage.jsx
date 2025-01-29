import React, { useState } from "react";
import axios from "axios";
import SignUp from "../../components/Auth/SignUp";

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

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Check password match
    if (formData.password !== formData.confirmPassword) {
      setErrorMessage("Passwords do not match!");
      return; // Do not submit the form if it does not match
    }

    try {
      // Send registration request to Backend API
      const response = await axios.post("http://localhost:8080/v1/users", {
        fullName: formData.fullName,
        email: formData.email,
        password: formData.password,
      });

      // Show message after successful registration
      setSuccessMessage("Sign-up successful! You can now log in.");
      setErrorMessage(""); // Clear Error message
      alert("Sign-up successful!");
      onSignUpSuccess(); // Routing process

      console.log("Response from backend:", response.data);
    } catch (error) {
      // Show message in case of error
      setErrorMessage(
        error.response?.data?.message || "An error occurred during sign-up!"
      );
      console.error("Error during sign-up:", error);
    }
  };

  return (
    <SignUp
      formData={formData}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
      errorMessage={errorMessage}
      successMessage={successMessage}
    />
  );
}
