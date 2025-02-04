import React, { useEffect, useState } from "react";
import Button from "./Button";
import axios from "axios";

const ProjectsSidebar = ({
  onStartAddProject,
  projects,
  onSelectProject,
  selectedProjectId,
  setProjectsState,
  onResetProject,
  handleLogout,
}) => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const userId = localStorage.getItem("userId");
        if (!userId) {
          console.error("User not logged in.");
          return;
        }
        const response = await axios.get(
          `http://localhost:8080/api/projects/user/${userId}`
        );
        setProjectsState((prevState) => ({
          ...prevState,
          projects: response.data,
        }));
      } catch (error) {
        console.error("Error fetching projects:", error);
      }
    };

    fetchProjects();
  }, [setProjectsState]);

  return (
    <>
      {/* Mobile Menu Button - Visible For Only Small Secreen */}
      <button
        className="lg:hidden fixed top-4 left-4 z-50 p-2 bg-slate-800 rounded-lg text-white"
        onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
      >
        {isMobileMenuOpen ? "✕" : "☰"}
      </button>

      {/* Overlay - Darken background when Mobile menu is open */}
      {isMobileMenuOpen && (
        <div
          className="lg:hidden fixed inset-0 bg-black bg-opacity-50 z-40"
          onClick={() => setIsMobileMenuOpen(false)}
        />
      )}

      <aside
        className={`
        fixed lg:static
        top-0 bottom-0 left-0
        w-72 sm:w-80 lg:w-96
        bg-gradient-to-b from-slate-800 to-slate-900
        text-stone-50 
        rounded-r-xl 
        flex flex-col
        transition-transform duration-300 ease-in-out
        z-50
        ${
          isMobileMenuOpen
            ? "translate-x-0"
            : "-translate-x-full lg:translate-x-0"
        }
      `}
      >
        {/* Header Section */}
        <div className="p-4 sm:p-6 border-b border-slate-700">
          <h2 className="text-lg sm:text-xl font-semibold text-white mb-4 sm:mb-6 flex items-center">
            <span className="bg-blue-500 w-2 h-2 rounded-full mr-2"></span>
            Your Projects
          </h2>
          <div className="flex flex-col sm:flex-row gap-2">
            <Button
              onClick={onStartAddProject}
              className="bg-blue-600 hover:bg-blue-700 transition-colors duration-200 
                       text-xs sm:text-sm px-3 sm:px-4 py-2 rounded-lg flex items-center 
                       justify-center gap-2 w-full sm:w-auto"
            >
              <span className="text-lg">+</span> Add Project
            </Button>
            <Button
              onClick={onResetProject}
              className="bg-slate-700 hover:bg-slate-600 transition-colors duration-200 
                       text-xs sm:text-sm px-3 sm:px-4 py-2 rounded-lg flex items-center 
                       justify-center gap-2 w-full sm:w-auto"
            >
              <span>←</span> Home
            </Button>
          </div>
        </div>

        {/* Projects List */}
        <div className="flex-1 overflow-y-auto p-3 sm:p-4">
          <ul className="space-y-2">
            {projects.map((project) => (
              <li key={project.id}>
                <button
                  className={`w-full p-2 sm:p-3 rounded-lg transition-all duration-200 
                            flex items-center gap-2 sm:gap-3 text-left
                    ${
                      project.id === selectedProjectId
                        ? "bg-blue-600 text-white shadow-lg shadow-blue-500/20"
                        : "bg-slate-800/50 text-slate-300 hover:bg-slate-700"
                    }`}
                  onClick={() => {
                    onSelectProject(project.id);
                    setIsMobileMenuOpen(false);
                  }}
                >
                  <div
                    className={`w-2 h-2 rounded-full flex-shrink-0 ${
                      project.id === selectedProjectId
                        ? "bg-white"
                        : "bg-slate-500"
                    }`}
                  ></div>
                  <span className="text-xs sm:text-sm font-medium truncate">
                    {project.title}
                  </span>
                </button>
              </li>
            ))}
          </ul>
        </div>
        {/* Logout Button */}
        <div className="absolute bottom-4 right-4 sm:bottom-6 sm:right-6 flex justify-end w-full px-4">
          <Button
            onClick={handleLogout}
            className="bg-red-600 hover:bg-red-700 transition-colors duration-200 text-xs sm:text-sm px-4 py-2 rounded-lg flex items-center justify-center gap-2 w-auto"
          >
            Logout
          </Button>
        </div>
      </aside>
    </>
  );
};

export default ProjectsSidebar;
