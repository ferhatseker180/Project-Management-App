# 🏗️ Project Management App

A full-stack project management application built with **React (Frontend)** and **Spring Boot (Backend)**. This app allows users to **register, log in, create projects, manage tasks, and track progress**.

## 🚀 Features
- 🔐 **Authentication & Authorization:** User login, registration, and authentication using JWT.
- 📁 **Project Management:** Create, edit, and delete projects.
- ✅ **Task Management:** Add, update, delete, and mark tasks as completed.
- 📊 **Task Progress Bar:** Displays the number of tasks and completed tasks within a project.
- 🛠️ **REST API Integration:** Seamless communication between frontend and backend.
- 🎨 **Responsive UI:** Optimized for desktop and mobile devices.

## 🏗️ Technologies Used
### Frontend:
- **React.js** (with Hooks & Context API)
- **Tailwind CSS** (for styling)
- **React Router** (for navigation)
- **Axios** (for API requests)
- **React Toastify** (for notifications)

### Backend:
- **Java 17 & Spring Boot**
- **Spring Security & JWT** (for authentication)
- **Spring Data JPA & Hibernate** (for database operations)
- **PostgreSQL** (as the database)
- **Lombok & ModelMapper** (for cleaner code)

## 📌 API Endpoints
### **Authentication**
| Method | Endpoint                        | Description                    |
|--------|---------------------------------|--------------------------------|
| POST   | `/v1/users`                     | Register a new user            |
| POST   | `/v1/users/login`               | User login and token generation|

### **Projects**
| Method | Endpoint                        | Description                    |
|--------|---------------------------------|--------------------------------|
| GET    | `/api/projects/user/{userId}`   | Get all projects for the user  |
| POST   | `/api/projects/add`             | Create a new project           |
| GET    | `/api/projects/{id}`            | Get a specific project by ID   |
| PUT    | `/api/projects/update`          | Update a project by ID         |
| DELETE | `/api/projects/{id}`            | Delete a project by ID         |

### **Tasks**
| Method | Endpoint                        | Description                    |
|--------|-------------------------------  |--------------------------------|
| GET    | `/api/tasks/project/{projectId}'|Get all tasks for a project     |
| POST   | `/api/tasks/add`                | Add a new task to a project    |
| PUT    | `/api/tasks/{taskId}`           | Update a task by ID            |
| DELETE | `/api/tasks/{taskId}`           | Delete a task by ID            |

## Project Images

<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Sign-Up%20Page.PNG"> 
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Login%20Page.PNG"> 
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Succesfull%20Login.PNG"> 
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Failed%20Login.PNG"> 
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Main%20Screen.PNG"> 
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Create%20Project.PNG"> 
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Project%20Page.PNG">
<img src="https://raw.githubusercontent.com/ferhatseker180/Project-Management-App/refs/heads/main/Frontend/project-management-app/src/project_images/Zero%20Task%20Page.PNG">


## 📂 Project Structure
```bash
Project-Management-App/
│── backend/
│   ├── src/main/java/com/example/projectmanagement/
│   │   ├── controller/      # API controllers
│   │   ├── business/        # Business logic
│   │   ├── repository/      # Database access layer
│   │   ├── core/            # Security & application config & Exceptions
│   │   ├── dto/             # Data Transfer Objects (DTOs)
│   │   ├── entities/        # Entity classes
│   │   ├── security/        # JWT Layer
│   ├── src/main/resources/
│   │   ├── application.properties  # Database & app settings
│   ├── pom.xml  # Maven dependencies
│
│── frontend/
│   ├── src/
│   │   ├── components/      # UI components
│   │   ├── pages/           # Page-level components
│   │   ├── project_images/  #Project's Images
│   │   ├── App.jsx          # Main React component
│   ├── package.json  # Frontend dependencies
│
│── README.md

## 👤 Author
- **Ferhat Şeker**
- 📧 Contact: [LinkedIn](https://www.linkedin.com/in/ferhatseker180) | [GitHub](https://github.com/ferhatseker180)


