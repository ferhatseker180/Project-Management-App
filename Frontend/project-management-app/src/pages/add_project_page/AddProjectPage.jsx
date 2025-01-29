import React from "react";
import axios from "axios";
import NewProject from "../../components/NewProject";

export default function AddProjectPage({ projectsState, setProjectsState, setCurrentPage }) {
  const handleAddProject = async (projectData) => {
    console.log("Gönderilen Project Data:", projectData); // 👀 Backend'e gönderilen veri

    try {
        const response = await axios.post("http://localhost:8080/api/projects/add", projectData, {
            headers: { "Content-Type": "application/json" },
        });

        console.log("Projeye API'den gelen yanıt:", response.data);

        setProjectsState((prevState) => ({
            ...prevState,
            projects: [...prevState.projects, response.data],
        }));

        setCurrentPage("main");
    } catch (error) {
        console.error("Proje ekleme hatası:", error.response?.data || error.message);
        alert("Proje eklenirken bir hata oluştu!");
    }
};

  const handleCancelAddProject = () => {
    setCurrentPage("main");
  };

  return (
    <main className="h-screen my-8 flex justify-center">
      <NewProject onAdd={handleAddProject} onCancel={handleCancelAddProject} />
    </main>
  );
}
