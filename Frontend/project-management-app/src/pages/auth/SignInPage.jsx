import React, { useState } from "react";
import axios from "axios";
import SignIn from "../../components/Auth/SignIn";

export default function SignInPage({ setCurrentPage }) {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null); // Hata mesajını sıfırla
  
    try {
      const response = await axios.post("http://localhost:8080/v1/users/login", formData, {
        headers: { "Content-Type": "application/json" },
      });
  
      console.log("Login Successful:", response.data);
      const userId = response.data.data.id;  // Kullanıcı ID'sini alın
      localStorage.setItem("userId", userId);  // localStorage'a kaydedin
  
      alert("Login successful!");
      setCurrentPage("main");
    } catch (err) {
      console.error("Login Error:", err.response?.data || err.message);
      setError(err.response?.data?.message || "Login failed! Please try again.");
      alert("Login failed! Please try again.");
    }
  };

  return (
    <div>
      <SignIn onLogin={handleSubmit} onSignUp={() => setCurrentPage("signUp")} handleChange={handleChange} />
      {error && <p className="text-red-500 text-center mt-2">{error}</p>}
    </div>
  );
}
