package org.ferhat.project_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.ferhat.project_management_app.core.config.swagger", "org.ferhat.project_management_app"})
public class ProjectManagementAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementAppApplication.class, args);
    }

}
