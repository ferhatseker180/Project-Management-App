package org.ferhat.project_management_app.business.abstracts;

import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.project.ProjectSaveRequest;
import org.ferhat.project_management_app.dto.response.project.ProjectResponse;

public interface IProjectService {

    ResultData<ProjectResponse> addProject(ProjectSaveRequest projectSaveRequest);
    ProjectResponse getProjectById(Long id);
    ResultData<String> deleteProjectById(Long id);

}
