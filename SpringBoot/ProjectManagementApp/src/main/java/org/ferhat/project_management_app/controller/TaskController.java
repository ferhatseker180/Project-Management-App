package org.ferhat.project_management_app.controller;

import org.ferhat.project_management_app.business.abstracts.ITaskService;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.task.TaskSaveRequest;
import org.ferhat.project_management_app.dto.response.task.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResultData<TaskResponse>> addTask(@RequestBody TaskSaveRequest taskSaveRequest) {
        ResultData<TaskResponse> result = taskService.addTask(taskSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ResultData<List<TaskResponse>>> getTasksByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    // Patch Mapping provides updates to the choosen part
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<ResultData<TaskResponse>> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam boolean completed
    ) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, completed));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResultData<String>> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }
}
