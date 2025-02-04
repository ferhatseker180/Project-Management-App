package org.ferhat.project_management_app.business.abstracts;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.task.TaskSaveRequest;
import org.ferhat.project_management_app.dto.response.task.TaskResponse;
import java.util.List;

public interface ITaskService {

    ResultData<TaskResponse> addTask(TaskSaveRequest taskSaveRequest);
    ResultData<List<TaskResponse>> getTasksByProjectId(Long projectId);
    ResultData<String> deleteTask(Long taskId);
    ResultData<TaskResponse> updateTaskStatus(Long taskId, boolean completed);
}
