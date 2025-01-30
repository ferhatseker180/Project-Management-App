package org.ferhat.project_management_app.controller;

import jakarta.validation.Valid;
import org.ferhat.project_management_app.business.abstracts.IProjectService;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.project.ProjectResultHelper;
import org.ferhat.project_management_app.dto.request.project.ProjectSaveRequest;
import org.ferhat.project_management_app.dto.request.project.ProjectUpdateRequest;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResultData<ProjectResponse>> addProject(@RequestBody ProjectSaveRequest projectSaveRequest) {
        ResultData<ProjectResponse> result = projectService.addProject(projectSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<ResultData<ProjectResponse>> updateProject(@Valid @RequestBody ProjectUpdateRequest projectUpdateRequest) {
        return ResponseEntity.ok(projectService.updateProject(projectUpdateRequest));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByUserId(@PathVariable Long userId) {
        List<ProjectResponse> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultData<ProjectResponse>> getProjectById(@PathVariable("id") Long id) {
        ResultData<ProjectResponse> result = ProjectResultHelper.success(projectService.getProjectById(id));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultData<String>> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProjectById(id));
    }
}
