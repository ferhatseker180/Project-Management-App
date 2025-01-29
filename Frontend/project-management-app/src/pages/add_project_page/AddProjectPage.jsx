import React from "react";
import axios from "axios";
import NewProject from "../../components/NewProject";

export default function AddProjectPage({ projectsState, setProjectsState, setCurrentPage }) {
  const handleAddProject = async (projectData) => {
    console.log("Submitted Project Data:", projectData);

    try {
        const response = await axios.post("http://localhost:8080/api/projects/add", projectData, {
            headers: { "Content-Type": "application/json" },
        });

        console.log("API response to the project:", response.data);

        setProjectsState((prevState) => ({
            ...prevState,
            projects: [...prevState.projects, response.data],
        }));

        setCurrentPage("main");
    } catch (error) {
        console.error("Error adding a project:", error.response?.data || error.message);
        alert("An error occurred while adding a project!");
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
