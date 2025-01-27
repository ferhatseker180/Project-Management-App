package org.ferhat.project_management_app.controller;

import org.ferhat.project_management_app.business.abstracts.IProjectService;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.project.ProjectResultHelper;
import org.ferhat.project_management_app.dto.request.project.ProjectSaveRequest;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;
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
    public ResponseEntity<ResultData<ProjectResponse>> addProject(@RequestBody ProjectSaveRequest projectSaveRequest) {
        ResultData<ProjectResponse> result = projectService.addProject(projectSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
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
