package org.ferhat.project_management_app.dto.response.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;
    private String text;
    @JsonBackReference
    private ProjectResponse project;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public ProjectResponse getProject() {
        return project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setProject(ProjectResponse project) {
        this.project = project;
    }
}
