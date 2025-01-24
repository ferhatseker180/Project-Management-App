package org.ferhat.project_management_app.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    private String text;
    private Long projectId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
