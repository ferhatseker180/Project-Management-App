import React from 'react';
import noProjectImage from '../assets/empty-notebook.png';
import Button from './Button';

const NoProjectSelected = ({ onStartAddProject }) => {
  return (
    <div className="flex-1 flex items-center justify-center p-4 sm:p-8 -mt-16">
      <div className="max-w-md w-full mx-auto bg-white rounded-2xl shadow-lg p-6 sm:p-8 
                      transform transition-all duration-300 hover:scale-105">
        <div className="flex flex-col items-center text-center">
          {/* Image Container with gradient background */}
          <div className="w-24 h-24 sm:w-32 sm:h-32 rounded-full bg-gradient-to-br from-blue-50 to-blue-100 
                          flex items-center justify-center mb-6 shadow-inner">
            <img 
              src={noProjectImage} 
              alt="An empty task list" 
              className="w-12 h-12 sm:w-16 sm:h-16 object-contain opacity-80"
            />
          </div>

          {/* Text Content */}
          <h2 className="text-xl sm:text-2xl font-bold text-slate-700 mb-3">
            No Project Selected
          </h2>
          
          <p className="text-sm sm:text-base text-slate-500 mb-8 max-w-sm">
            Select a project or get started with a new one to begin organizing your tasks
          </p>

          {/* Button with enhanced styling */}
          <Button 
            onClick={onStartAddProject}
            className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg
                     shadow-sm hover:shadow-md transition-all duration-200
                     flex items-center justify-center gap-2 w-full sm:w-auto"
          >
            <span className="text-xl">+</span>
            Create a new project
          </Button>
        </div>
      </div>
    </div>
  );
};

export default NoProjectSelected;