package org.ferhat.project_management_app.dto.request.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectSaveRequest {

    @NotBlank(message = "Project Title is necessary")
    @Size(min = 2, max = 100, message = "Title Must Be 2-100 Character")
    private String title;

    @Size(max = 500, message = "Description Must Be Max 500 Character")
    private String description;

    @NotNull(message = "Due Date is necessary")
    private LocalDate dueDate;

    @NotNull(message = "User ID is necessary")
    private Long userId;

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

    public @NotNull(message = "User ID is necessary") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID is necessary") Long userId) {
        this.userId = userId;
    }
}
