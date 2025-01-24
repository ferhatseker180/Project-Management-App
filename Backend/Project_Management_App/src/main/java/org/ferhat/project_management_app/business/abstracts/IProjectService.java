package org.ferhat.project_management_app.business.abstracts;

import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.ProjectRequest;
import org.ferhat.project_management_app.dto.response.ProjectResponse;

public interface IProjectService {
    ResultData<ProjectResponse> addProject(ProjectRequest projectRequest);
    ResultData<ProjectResponse> getProjectById(Long id);
    ResultData<String> deleteProjectById(Long id);
}
