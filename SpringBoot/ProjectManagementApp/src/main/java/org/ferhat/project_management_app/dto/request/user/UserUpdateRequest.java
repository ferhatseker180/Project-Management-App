package org.ferhat.project_management_app.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "User ID is required")
    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 80, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password; // Optional password update

    public @NotNull(message = "User ID is required") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "User ID is required") Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Full name is required") @Size(min = 2, max = 80, message = "Full name must be between 2 and 100 characters") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Full name is required") @Size(min = 2, max = 80, message = "Full name must be between 2 and 100 characters") String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @Size(min = 6, message = "Password must be at least 6 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Password must be at least 6 characters long") String password) {
        this.password = password;
    }
}
