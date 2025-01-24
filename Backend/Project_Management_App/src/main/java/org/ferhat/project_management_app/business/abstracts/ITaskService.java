package org.ferhat.project_management_app.business.abstracts;

import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.TaskRequest;
import org.ferhat.project_management_app.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {

    ResultData<TaskResponse> addTask(TaskRequest taskRequest);
    ResultData<List<TaskResponse>> getTasksByProjectId(Long projectId);
    ResultData<String> deleteTask(Long taskId);
}
