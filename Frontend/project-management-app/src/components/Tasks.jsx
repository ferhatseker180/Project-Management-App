import React, { useState } from "react";
import NewTask from "./NewTask"; 

export default function Tasks({ tasks, onAdd, onDelete, onCompleteTask }) {
  const totalTasks = tasks.length;
  const completedTasks = tasks.filter(task => task.completed).length;

  return (
    <section>
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-2xl font-bold text-stone-700">Tasks</h2>
        <div className="flex space-x-2">
          <div className="bg-blue-50 text-blue-600 px-0 py-0 rounded-full text-sm font-semibold shadow-sm">
            Toplam: {totalTasks}
          </div>
          <div className="bg-green-50 text-green-600 px-0 py-0 rounded-full text-sm font-semibold shadow-sm">
            Tamamlanan: {completedTasks}
          </div>
        </div>
      </div>
      
      <NewTask onAdd={onAdd} />
      {tasks.length === 0 && <p className="text-stone-800 my-4">This project does not have any tasks yet.</p>}
      {tasks.length > 0 && (
        <ul className="p-4 mt-8 rounded-md bg-stone-100">
          {tasks.map((task) => (
            <li 
              key={task.id} 
              className={`flex justify-between my-4 ${task.completed ? 'line-through text-gray-500' : ''}`}
            >
              <div className="flex items-center">
                <input
                  type="checkbox"
                  checked={task.completed}
                  onChange={() => onCompleteTask(task.id, !task.completed)}
                  className="mr-2"
                />
                <span>{task.text}</span>
              </div>
              <button 
                className="text-stone-700 hover:text-red-500" 
                onClick={() => onDelete(task.id)}
              >
                Clear
              </button>
            </li>
          ))}
        </ul>
      )}
    </section>
  );
}