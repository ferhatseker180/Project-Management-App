import React, { useState } from "react";
import axios from "axios"; // Axios kütüphanesi eklendi
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

    // Şifre eşleşmesini kontrol et
    if (formData.password !== formData.confirmPassword) {
      setErrorMessage("Passwords do not match!");
      return; // Eğer eşleşmiyorsa formu gönderme
    }

    try {
      // Backend API'ye kayıt isteği gönder
      const response = await axios.post("http://localhost:8080/v1/users", {
        fullName: formData.fullName,
        email: formData.email,
        password: formData.password,
      });

      // Başarılı kayıt sonrası mesaj göster
      setSuccessMessage("Sign-up successful! You can now log in.");
      setErrorMessage(""); // Hata mesajını temizle
      alert("Sign-up successful!");
      onSignUpSuccess(); // Yönlendirme işlemi

      console.log("Response from backend:", response.data);
    } catch (error) {
      // Hata durumunda mesaj göster
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
