import React, { useEffect } from "react";
import SelectedProject from "../../components/SelectedProject";
import ProjectsSidebar from "../../components/ProjectsSidebar";
import NoProjectSelected from "../../components/NoProjectSelected";
import axios from "axios";

export default function MainPage({ projectsState, setProjectsState, setCurrentPage }) {
  const selectedProject = projectsState.projects.find(
    (project) => project.id === projectsState.selectedProjectId
  );

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const userId = localStorage.getItem("userId");
        if (!userId) {
          console.error("User not logged in.");
          return;
        }
        const { data } = await axios.get(`http://localhost:8080/api/projects/user/${userId}`);
        setProjectsState((prevState) => ({ ...prevState, projects: data }));
      } catch (error) {
        console.error("Error fetching projects:", error);
      }
    };
    fetchProjects();
  }, [setProjectsState]);

  useEffect(() => {
    const fetchTasks = async () => {
      if (!selectedProject) {
        // Eğer seçili proje yoksa tasks'ı temizle
        setProjectsState((prevState) => ({ ...prevState, tasks: [] }));
        return;
      }
      
      try {
        const { data } = await axios.get(`http://localhost:8080/api/tasks/project/${selectedProject.id}`);
        setProjectsState((prevState) => ({ 
          ...prevState, 
          tasks: Array.isArray(data.data) ? data.data : [] 
        }));
      } catch (error) {
        // Hata durumunda tasks'ı boş array yap
        setProjectsState((prevState) => ({ ...prevState, tasks: [] }));
        
        // 404 hatasını normal bir durum olarak ele al
        if (error.response?.status === 404) {
          console.log(`No tasks found for project ${selectedProject.id}`);
        } else {
          console.error("Error fetching tasks:", error);
        }
      }
    };
    fetchTasks();
  }, [selectedProject, setProjectsState]);

  const handleStartAddProject = () => setCurrentPage("addProject");

  const handleSelectProject = (id) => {
    setProjectsState((prevState) => ({ ...prevState, selectedProjectId: id }));
  };

  const handleDeleteProject = () => {
    try {
    axios.delete(`http://localhost:8080/api/projects/${selectedProject.id}`);
     setProjectsState((prevState) => ({
      ...prevState,
      selectedProjectId: undefined,
      projects: prevState.projects.filter((project) => project.id !== prevState.selectedProjectId),
    }));
    console.log("Project deleted successfully");
    } catch (error) {
      console.error("Error deleting task:", error);
    }
  };

  const handleAddTask = async (text) => {
    try {
      const newTask = { text, projectId: selectedProject?.id };
      const { data } = await axios.post("http://localhost:8080/api/tasks/add", newTask);
      setProjectsState((prevState) => ({
        ...prevState,
        tasks: [...(prevState.tasks || []), data.data],
      }));
    } catch (error) {
      console.error("Error adding task:", error);
    }
  };

  const handleDeleteTask = async (taskId) => {
    try {
      await axios.delete(`http://localhost:8080/api/tasks/${taskId}`);
      setProjectsState((prevState) => ({
        ...prevState,
        tasks: prevState.tasks.filter((task) => task.id !== taskId),
      }));
    } catch (error) {
      console.error("Error deleting task:", error);
    }
  };

  const content = selectedProject ? (
    <SelectedProject
      project={selectedProject}
      onDelete={handleDeleteProject}
      tasks={projectsState.tasks || []}
      onAddTask={handleAddTask}
      onDeleteTask={handleDeleteTask}
    />
  ) : (
    <NoProjectSelected onStartAddProject={handleStartAddProject} />
  );

  return (
    <main className="h-screen my-8 flex gap-8">
      <ProjectsSidebar
        onStartAddProject={handleStartAddProject}
        projects={projectsState.projects || []}
        onSelectProject={handleSelectProject}
        selectedProjectId={projectsState.selectedProjectId}
        setProjectsState={setProjectsState}
      />
      {content}
    </main>
  );
}