package org.ferhat.project_management_app.business.impl;

import org.ferhat.project_management_app.business.abstracts.ITaskService;
import org.ferhat.project_management_app.core.config.modelMapper.IModelMapperService;
import org.ferhat.project_management_app.core.exceptions.NotFoundException;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.task.TaskMessage;
import org.ferhat.project_management_app.core.utils.task.TaskResultHelper;
import org.ferhat.project_management_app.dto.request.TaskRequest;
import org.ferhat.project_management_app.dto.response.TaskResponse;
import org.ferhat.project_management_app.entities.Project;
import org.ferhat.project_management_app.entities.Task;
import org.ferhat.project_management_app.repository.ProjectRepository;
import org.ferhat.project_management_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskManager implements ITaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final IModelMapperService modelMapperService;

    public TaskManager(TaskRepository taskRepository, ProjectRepository projectRepository, IModelMapperService modelMapperService) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public ResultData<TaskResponse> addTask(TaskRequest taskRequest) {
        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new NotFoundException(TaskMessage.TASK_NOT_FOUND));

        Task task = modelMapperService.forRequest().map(taskRequest, Task.class);
        task.setProject(project);

        Task savedTask = taskRepository.save(task);
        TaskResponse taskResponse = modelMapperService.forResponse().map(savedTask, TaskResponse.class);
        return TaskResultHelper.created(taskResponse);
    }

    @Override
    public ResultData<List<TaskResponse>> getTasksByProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        if (tasks.isEmpty()) {
            throw new NotFoundException(TaskMessage.TASK_NOT_FOUND);
        }
        List<TaskResponse> taskResponses = tasks.stream()
                .map(task -> modelMapperService.forResponse().map(task, TaskResponse.class))
                .collect(Collectors.toList());
        return TaskResultHelper.success(taskResponses);
    }

    @Override
    public ResultData<String> deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException(TaskMessage.TASK_NOT_FOUND);
        }
        taskRepository.deleteById(taskId);
        return TaskResultHelper.deleted(TaskMessage.TASK_DELETED);
    }
}
