import React, { useState } from 'react';
import Tasks from "./Tasks";

export default function SelectedProject({project, onDelete, onAddTask, onDeleteTask, tasks}) {
    const [isEditing, setIsEditing] = useState(false);
    const [editedProject, setEditedProject] = useState({
        title: project.title,
        description: project.description,
        dueDate: project.dueDate.split('T')[0] // ISO formatından date input formatına çevirme
    });

    const formattedDate = new Date(project.dueDate).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });

    const handleUpdate = () => {
        // Bu fonksiyon backend servisi ile bağlanacak
        console.log('Updated project:', editedProject);
        setIsEditing(false);
    };

    return (
        <div className="w-full max-w-4xl px-4 sm:px-6 mt-8 sm:mt-16">
            <div className="bg-white rounded-xl shadow-lg p-6 sm:p-8">
                <header className="pb-6 mb-6 border-b border-slate-200">
                    <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
                        {isEditing ? (
                            <input
                                type="text"
                                value={editedProject.title}
                                onChange={(e) => setEditedProject({...editedProject, title: e.target.value})}
                                className="text-2xl sm:text-3xl font-bold text-slate-700 bg-slate-50 px-3 py-2 rounded-lg border border-slate-200 focus:border-blue-500 focus:ring-blue-500 outline-none"
                            />
                        ) : (
                            <h1 className="text-2xl sm:text-3xl font-bold text-slate-700">{project.title}</h1>
                        )}
                        
                        <div className="flex items-center gap-2 sm:gap-4">
                            <button
                                onClick={() => isEditing ? handleUpdate() : setIsEditing(true)}
                                className="px-4 py-2 text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 rounded-lg shadow-sm hover:shadow-md transition-all duration-200"
                            >
                                {isEditing ? 'Save' : 'Edit'}
                            </button>
                            <button 
                                onClick={onDelete}
                                className="px-4 py-2 text-sm font-medium text-red-600 hover:text-white hover:bg-red-600 rounded-lg border border-red-600 transition-all duration-200"
                            >
                                Delete
                            </button>
                        </div>
                    </div>

                    <div className="mt-4 space-y-4">
                        {isEditing ? (
                            <>
                                <input
                                    type="date"
                                    value={editedProject.dueDate}
                                    onChange={(e) => setEditedProject({...editedProject, dueDate: e.target.value})}
                                    className="block w-full sm:w-auto px-3 py-2 text-slate-600 bg-slate-50 rounded-lg border border-slate-200 focus:border-blue-500 focus:ring-blue-500 outline-none"
                                />
                                <textarea
                                    value={editedProject.description}
                                    onChange={(e) => setEditedProject({...editedProject, description: e.target.value})}
                                    className="w-full px-3 py-2 text-slate-600 bg-slate-50 rounded-lg border border-slate-200 focus:border-blue-500 focus:ring-blue-500 outline-none min-h-[100px]"
                                />
                            </>
                        ) : (
                            <>
                                <p className="text-slate-500">{formattedDate}</p>
                                <p className="text-slate-600 whitespace-pre-wrap">{project.description}</p>
                            </>
                        )}
                    </div>
                </header>

                <div className="bg-slate-50 rounded-lg p-4 sm:p-6">
                    <Tasks 
                        onAdd={onAddTask} 
                        onDelete={onDeleteTask} 
                        tasks={tasks}
                    />
                </div>
            </div>
        </div>
    );
}