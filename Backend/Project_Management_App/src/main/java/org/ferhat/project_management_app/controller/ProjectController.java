package org.ferhat.project_management_app.controller;

import org.ferhat.project_management_app.business.abstracts.IProjectService;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.ProjectRequest;
import org.ferhat.project_management_app.dto.response.ProjectResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResultData<ProjectResponse>> addProject(@RequestBody ProjectRequest projectRequest) {
        ResultData<ProjectResponse> result = projectService.addProject(projectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultData<ProjectResponse>> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultData<String>> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProjectById(id));
    }
}
