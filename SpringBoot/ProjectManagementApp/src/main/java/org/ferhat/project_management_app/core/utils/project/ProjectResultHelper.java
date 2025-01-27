package org.ferhat.project_management_app.core.utils.project;

import org.ferhat.project_management_app.core.result.ResultData;

public class ProjectResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, ProjectMessage.PROJECT_CREATED, "201", data);
    }

    public static <T> ResultData<T> deleted(T data){
        return new ResultData<>(true, ProjectMessage.PROJECT_DELETED, "204", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, ProjectMessage.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, ProjectMessage.PROJECT_FOUND, "200", data);
    }

    public static <T> ResultData<T> unSuccess(T data) {
        return new ResultData<>(true, ProjectMessage.PROJECT_NOT_FOUND, "404", data);
    }
}
