package org.ferhat.project_management_app.repository;

import org.ferhat.project_management_app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
