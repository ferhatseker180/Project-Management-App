package org.ferhat.project_management_app.dto.response.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import org.ferhat.project_management_app.dto.response.task.TaskResponse;
import org.ferhat.project_management_app.dto.response.user.UserResponse;
import java.time.LocalDate;
import java.util.List;

@Builder
public class ProjectResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;

    @JsonBackReference
    private UserResponse user;
    private List<TaskResponse> tasks;

    public ProjectResponse() {

    }

    public ProjectResponse(Long id, String title, String description, LocalDate dueDate, UserResponse user, List<TaskResponse> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.user = user;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public UserResponse getUser() {
        return user;
    }

    public List<TaskResponse> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public void setTasks(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }
}
