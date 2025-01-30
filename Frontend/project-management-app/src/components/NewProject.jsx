import React, { useRef } from 'react';
import Input from './Input';
import Modal from './Modal';

const NewProject = ({ onAdd, onCancel }) => {
  const modal = useRef();
  const title = useRef();
  const description = useRef();
  const dueDate = useRef();

  const handleSave = () => {
    const enteredTitle = title.current.value;
    const enteredDescription = description.current.value;
    const enteredDueDate = dueDate.current.value;
  
    if (enteredTitle.trim() === '' || enteredDescription.trim() === '' || enteredDueDate.trim() === '') {
      modal.current.open();
      return;
    }
  
    const userId = localStorage.getItem("userId");
  
    if (!userId) {
      modal.current.open();
      return;
    }
  
    onAdd({
      title: enteredTitle,
      description: enteredDescription,
      dueDate: enteredDueDate,
      userId: userId,
    });
  };

  const inputStyles = "px-4 w-full bg-slate-50 border-slate-200 focus:border-blue-500 focus:ring-blue-500 rounded-lg transition-colors duration-200";
  const textareaStyles = "px-4 py-2 w-full bg-slate-50 border-slate-200 focus:border-blue-500 focus:ring-blue-500 rounded-lg transition-colors duration-200 min-h-[120px]";

  return (
    <>
      <Modal ref={modal} buttonCaption="Close">
        <h2 className="text-xl font-bold text-slate-700 my-4">Invalid Input</h2>
        <p className="text-slate-600 mb-4">Oops ... looks like you forget to enter a value.</p>
        <p className="text-slate-600 mb-4">Please make sure you provide a valid value for every input field.</p>
      </Modal>
      
      <div className="w-full max-w-2xl mx-auto px-4 sm:px-6 mt-8 sm:mt-16">
        <div className="bg-white rounded-xl shadow-lg p-6 sm:p-8">
          <h2 className="text-2xl font-semibold text-slate-800 mb-6">Create New Project</h2>
          
          <div className="space-y-6">
            <Input 
              type="text" 
              ref={title} 
              label="Title" 
              className={inputStyles}
            />
            
            <Input 
              ref={description} 
              label="Description" 
              textArea 
              className={textareaStyles}
            />
            
            <Input 
              type="date" 
              ref={dueDate} 
              label="Due Date" 
              className={inputStyles}
            />
          </div>

          <div className="flex flex-col sm:flex-row items-center justify-end gap-3 mt-8 pt-6 border-t border-slate-200">
            <button 
              onClick={onCancel}
              className="w-full sm:w-auto px-6 py-2.5 text-sm font-medium text-slate-700 
                         hover:text-slate-900 transition-colors duration-200"
            >
              Cancel
            </button>
            
            <button 
              onClick={handleSave}
              className="w-full sm:w-auto px-6 py-2.5 text-sm font-medium text-white 
                         bg-blue-600 hover:bg-blue-700 rounded-lg shadow-sm 
                         hover:shadow-md transition-all duration-200"
            >
              Create Project
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default NewProject;