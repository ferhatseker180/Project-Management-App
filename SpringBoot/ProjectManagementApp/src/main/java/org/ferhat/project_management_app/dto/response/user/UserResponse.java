package org.ferhat.project_management_app.dto.response.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;
import java.util.List;

@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String token;

    @JsonManagedReference
    private List<ProjectResponse> projects;

    public UserResponse(Long id, String fullName, String email, String token, List<ProjectResponse> projects) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.token = token;
        this.projects = projects;
    }

    public UserResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProjects(List<ProjectResponse> projects) {
        this.projects = projects;
    }
}
