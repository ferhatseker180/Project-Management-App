package org.ferhat.project_management_app.dto.request.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskSaveRequest {

    @NotBlank(message = "Task Text is Required")
    @Size(min = 2, max = 500, message = "Task Text Must be Between 2-500 Characters")
    private String text;

    @NotNull(message = "Project's ID is Required")
    private Long projectId;

    public @NotBlank(message = "Task Text is Required") @Size(min = 2, max = 500, message = "Task Text Must be Between 2-500 Characters") String getText() {
        return text;
    }

    public void setText(@NotBlank(message = "Task Text is Required") @Size(min = 2, max = 500, message = "Task Text Must be Between 2-500 Characters") String text) {
        this.text = text;
    }

    public @NotNull(message = "Project's ID is Required") Long getProjectId() {
        return projectId;
    }

    public void setProjectId(@NotNull(message = "Project's ID is Required") Long projectId) {
        this.projectId = projectId;
    }
}
