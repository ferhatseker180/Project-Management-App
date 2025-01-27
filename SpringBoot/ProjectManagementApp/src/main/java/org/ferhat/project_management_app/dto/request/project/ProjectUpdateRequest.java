package org.ferhat.project_management_app.dto.request.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUpdateRequest {

    @NotNull(message = "Project's ID is necessary")
    private Long id;

    @NotBlank(message = "Project Title is necessary")
    @Size(min = 2, max = 100, message = "Title Must Be 2-100 Character")
    private String title;

    @Size(max = 500, message = "Description Must Be Max 500 Character")
    private String description;

    @NotNull(message = "Due Date is necessary")
    private LocalDate dueDate;

    public @NotNull(message = "Project's ID is necessary") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Project's ID is necessary") Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Project Title is necessary") @Size(min = 2, max = 100, message = "Title Must Be 2-100 Character") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Project Title is necessary") @Size(min = 2, max = 100, message = "Title Must Be 2-100 Character") String title) {
        this.title = title;
    }

    public @Size(max = 500, message = "Description Must Be Max 500 Character") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Description Must Be Max 500 Character") String description) {
        this.description = description;
    }

    public @NotNull(message = "Due Date is necessary") LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(@NotNull(message = "Due Date is necessary") LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
