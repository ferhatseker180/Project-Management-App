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
import org.ferhat.project_management_app.dto.response.user.UserResponse;
import org.ferhat.project_management_app.entities.Project;
import org.ferhat.project_management_app.entities.User;
import org.ferhat.project_management_app.repository.ProjectRepository;
import org.ferhat.project_management_app.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        // Kullanıcıyı bul
        User user = userRepository.findById(projectSaveRequest.getUserId())
                .orElseThrow(() -> new NotFoundException(UserMessage.USER_NOT_FOUND));

        // Request'i Project'e dönüştür
        Project project = modelMapperService.forRequest().map(projectSaveRequest, Project.class);

        // ID'nin null olduğundan emin ol
        project.setId(null);

        // Kullanıcı ve proje arasındaki ilişkiyi oluştur
        project.setUser(user);
        user.getProjects().add(project);

        // Projeyi kaydet
        Project savedProject = projectRepository.save(project);

        // Response döndür
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
        projectRepository.delete(project);  // CascadeType.ALL ile ilişkili tüm task'ler de silinir
        return ProjectResultHelper.deleted(ProjectMessage.PROJECT_DELETED);
    }
}
