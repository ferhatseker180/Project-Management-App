package org.ferhat.project_management_app.dto.response.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;
    private String text;
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
}
