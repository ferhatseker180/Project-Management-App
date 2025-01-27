package org.ferhat.project_management_app.core.utils.task;

import org.ferhat.project_management_app.core.result.ResultData;

public class TaskResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, TaskMessage.TASK_CREATED, "201", data);
    }

    public static <T> ResultData<T> deleted(T data){
        return new ResultData<>(true, TaskMessage.TASK_DELETED, "204", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, TaskMessage.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, TaskMessage.TASK_FOUND, "200", data);
    }

    public static <T> ResultData<T> unSuccess(T data) {
        return new ResultData<>(true, TaskMessage.TASK_NOT_FOUND, "404", data);
    }
}
