package org.ferhat.project_management_app.business.impl;

import org.ferhat.project_management_app.business.abstracts.IProjectService;
import org.ferhat.project_management_app.core.config.modelMapper.IModelMapperService;
import org.ferhat.project_management_app.core.exceptions.NotFoundException;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.project.ProjectMessage;
import org.ferhat.project_management_app.core.utils.project.ProjectResultHelper;
import org.ferhat.project_management_app.core.utils.user.UserMessage;
import org.ferhat.project_management_app.dto.request.project.ProjectSaveRequest;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;
import org.ferhat.project_management_app.entities.Project;
import org.ferhat.project_management_app.entities.User;
import org.ferhat.project_management_app.repository.ProjectRepository;
import org.ferhat.project_management_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectManager implements IProjectService {
    private final ProjectRepository projectRepository;
    private final IModelMapperService modelMapperService;
    private final UserRepository userRepository;

    public ProjectManager(ProjectRepository projectRepository, IModelMapperService modelMapperService, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.modelMapperService = modelMapperService;
        this.userRepository = userRepository;
    }

    @Override
    public ResultData<ProjectResponse> addProject(ProjectSaveRequest projectSaveRequest) {
        // Find User
        User user = userRepository.findById(projectSaveRequest.getUserId())
                .orElseThrow(() -> new NotFoundException(UserMessage.USER_NOT_FOUND));

        // Convert Request to Project
        Project project = modelMapperService.forRequest().map(projectSaveRequest, Project.class);

        // Make sure the ID is null
        project.setId(null);

        // Create the relationship between the user and the project
        project.setUser(user);
        user.getProjects().add(project);

        Project savedProject = projectRepository.save(project);

        // Response return
        ProjectResponse projectResponse = modelMapperService.forResponse().map(project, ProjectResponse.class);
        return ProjectResultHelper.created(projectResponse);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProjectMessage.PROJECT_NOT_FOUND));

        return modelMapperService.forResponse().map(project, ProjectResponse.class);
    }

    @Override
    public ResultData<String> deleteProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProjectMessage.PROJECT_NOT_FOUND));
        projectRepository.delete(project);  // All tasks associated with CascadeType.ALL will also be deleted
        return ProjectResultHelper.deleted(ProjectMessage.PROJECT_DELETED);
    }

    @Override
    public List<ProjectResponse> getProjectsByUserId(Long userId) {
        List<Project> projects = projectRepository.findByUserId(userId);
        return projects.stream()
                .map(project -> modelMapperService.forResponse().map(project, ProjectResponse.class))
                .collect(Collectors.toList());
    }
}
