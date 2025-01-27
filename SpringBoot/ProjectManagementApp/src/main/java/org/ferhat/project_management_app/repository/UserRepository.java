package org.ferhat.project_management_app.repository;

import org.ferhat.project_management_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
