package org.ferhat.project_management_app.dto.request.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequest {

    @NotNull(message = "Task ID is Required")
    private Long id;

    @NotBlank(message = "Task Text is Required")
    @Size(min = 2, max = 500, message = "Task Text Must be Between 2-500 Characters")
    private String text;

    public @NotNull(message = "Task ID is Required") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Task ID is Required") Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Task Text is Required") @Size(min = 2, max = 500, message = "Task Text Must be Between 2-500 Characters") String getText() {
        return text;
    }

    public void setText(@NotBlank(message = "Task Text is Required") @Size(min = 2, max = 500, message = "Task Text Must be Between 2-500 Characters") String text) {
        this.text = text;
    }
}
