import React, { useEffect } from "react";
import SelectedProject from "../../components/SelectedProject";
import ProjectsSidebar from "../../components/ProjectsSidebar";
import NoProjectSelected from "../../components/NoProjectSelected";
import axios from "axios";

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function MainPage({ projectsState, setProjectsState, setCurrentPage }) {
  const selectedProject = projectsState.projects.find(
    (project) => project.id === projectsState.selectedProjectId
  );

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const userId = localStorage.getItem("userId");
        const token = localStorage.getItem("token");
        if (!userId || !token) {
          console.error("User not logged in.");
          return;
        }
        // Fetch task if there is no selected project
        if (!selectedProject || !selectedProject.id) {
          console.warn("No selected project found, skipping task fetch.");
          return;
        }
        const { data } = await axios.get(`http://localhost:8080/api/projects/user/${userId}`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log("Fetched projects:", data);
        setProjectsState((prevState) => ({ 
          ...prevState, 
          projects: data,
          selectedProjectId: prevState.selectedProjectId // protect selectedProjectId
        }));
      } catch (error) {
        if (error.response?.status === 403) {
          console.error("Authentication error:", error);
          // Redirect the user to the login page if the token is expired or invalid
          setCurrentPage("login");
        } else {
          console.error("Error fetching projects:", error);
        }
      }
    };
    fetchProjects();
  }, [setProjectsState]);

  useEffect(() => {
    const fetchTasks = async () => {
      if (!selectedProject) {
        // Clear tasks if there is no project selected
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
        // Make tasks empty array on error
        setProjectsState((prevState) => ({ ...prevState, tasks: [] }));
        
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

  const handleUpdateProject = async (updatedProject) => {
    try {
      console.log("Sending update request to backend...");
      // Düzeltilmiş endpoint URL'si
      const response = await axios.put(
        "http://localhost:8080/api/projects/update", 
        {
          id: updatedProject.id,
          title: updatedProject.title,
          description: updatedProject.description,
          dueDate: updatedProject.dueDate
        }
      );
      console.log("Backend response:", response.data);
  
      // Update Frontend's State
      setProjectsState((prevState) => ({
        ...prevState,
        projects: prevState.projects.map((project) =>
          project.id === updatedProject.id ? updatedProject : project
        ),
      }));
      toast.success('🎉 Updated successful!', {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: false,
              progress: undefined,
              theme: "colored",
              onClose: () => setCurrentPage("main")
            });
    } catch (error) {
      toast.error('❌ Updated failed!', {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: false,
              progress: undefined,
              theme: "colored"
            });
      console.error("Error updating project:", error.response?.data || error.message);
    }
  };
  

  const handleDeleteProject = () => {
    try {
      // If there is no selected project, query for deletion
      if (!selectedProject?.id) {
        console.error("No project selected for deletion.");
        return;
      }
      axios.delete(`http://localhost:8080/api/projects/${selectedProject.id}`);
      setProjectsState((prevState) => ({
        ...prevState,
        selectedProjectId: undefined,
        projects: prevState.projects.filter((project) => project.id !== prevState.selectedProjectId),
      }));

      toast.success('Deleting the project was completed successfully', {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored",
        onClose: () => setCurrentPage("main") // Redirect after Toast is closed
      });
      console.log("Project deleted successfully");
    } catch (error) {
      toast.error('❌ Project deletion failed!', {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored"
      });
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

      toast.success(' The task added successfully', {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored",
        onClose: () => setCurrentPage("main")
      });
    } catch (error) {
      toast.error('❌ Task adding failed!', {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored"
      });
      console.error("Error adding task:", error);
    }
  };

  const handleCompleteTask = async (taskId, completed) => {
    try {
      const { data } = await axios.patch(`http://localhost:8080/api/tasks/${taskId}/status`, 
        null, 
        { params: { completed } }
      );
      
      setProjectsState((prevState) => ({
        ...prevState,
        tasks: prevState.tasks.map((task) => 
          task.id === taskId ? { ...task, completed: data.data.completed } : task
        ),
      }));
    } catch (error) {
      console.error("Error updating task status:", error);
    }
  };

  const handleDeleteTask = async (taskId) => {
    try {
      await axios.delete(`http://localhost:8080/api/tasks/${taskId}`);
      setProjectsState((prevState) => ({
        ...prevState,
        tasks: prevState.tasks.filter((task) => task.id !== taskId),
      }));
      toast.success(' Task deleted successfully', {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored",
        onClose: () => setCurrentPage("main")
      });
    } catch (error) {
      toast.error('❌ Task deletion failed', {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: false,
        progress: undefined,
        theme: "colored"
      });
      console.error("Error deleting task:", error);
    }
  };

  const handleResetProject = () => {
    setProjectsState((prevState) => ({
      ...prevState,
      selectedProjectId: undefined,
      tasks: []
    }));
  };

  const content = selectedProject ? (
    <SelectedProject
      project={selectedProject}
      onDelete={handleDeleteProject}
      tasks={projectsState.tasks || []}
      onAddTask={handleAddTask}
      onCompleteTask={handleCompleteTask}
      onDeleteTask={handleDeleteTask}
      onUpdateProject={handleUpdateProject}
    />
  ) : (
    <NoProjectSelected onStartAddProject={handleStartAddProject} />
  );

  return (
    <main className="h-screen my-2 flex gap-8">
      <ProjectsSidebar
        onStartAddProject={handleStartAddProject}
        projects={projectsState.projects || []}
        onSelectProject={handleSelectProject}
        selectedProjectId={projectsState.selectedProjectId}
        setProjectsState={setProjectsState}
        onResetProject={handleResetProject}
        handleLogout={() => setCurrentPage("login")}
      />
      {content}

      <ToastContainer
              position="top-center"
              autoClose={1000}
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
    </main>
  );
}