package org.ferhat.project_management_app.business.impl;

import org.ferhat.project_management_app.business.abstracts.IProjectService;
import org.ferhat.project_management_app.core.config.modelMapper.IModelMapperService;
import org.ferhat.project_management_app.core.exceptions.NotFoundException;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.project.ProjectMessage;
import org.ferhat.project_management_app.core.utils.project.ProjectResultHelper;
import org.ferhat.project_management_app.core.utils.user.UserMessage;
import org.ferhat.project_management_app.dto.request.ProjectRequest;
import org.ferhat.project_management_app.dto.response.ProjectResponse;
import org.ferhat.project_management_app.entities.Project;
import org.ferhat.project_management_app.entities.Users;
import org.ferhat.project_management_app.repository.ProjectRepository;
import org.ferhat.project_management_app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ResultData<ProjectResponse> addProject(ProjectRequest projectRequest) {
        // Önce user'ı bulalım
        Users user = userRepository.findById(projectRequest.getUserId())
                .orElseThrow(() -> new NotFoundException(UserMessage.USER_NOT_FOUND));

        // ProjectRequest'i Project'e dönüştür
        Project project = modelMapperService.forRequest().map(projectRequest, Project.class);

        // User'ı manuel olarak set et
        project.setUser(user);

        // Projeyi kaydet
        Project savedProject = projectRepository.save(project);

        // Response'a dönüştür
        ProjectResponse projectResponse = modelMapperService.forResponse().map(savedProject, ProjectResponse.class);
        return ProjectResultHelper.created(projectResponse);
    }

    @Override
    public ResultData<ProjectResponse> getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProjectMessage.PROJECT_NOT_FOUND));
        ProjectResponse projectResponse = modelMapperService.forResponse().map(project, ProjectResponse.class);
        return ProjectResultHelper.success(projectResponse);
    }

    @Override
    public ResultData<String> deleteProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProjectMessage.PROJECT_NOT_FOUND));
        projectRepository.delete(project);  // CascadeType.ALL ile ilişkili tüm task'ler de silinir
        return ProjectResultHelper.deleted(ProjectMessage.PROJECT_DELETED);
    }
}
