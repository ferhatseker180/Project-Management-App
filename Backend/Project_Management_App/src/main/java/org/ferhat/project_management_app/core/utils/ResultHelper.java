package org.ferhat.project_management_app.core.utils;

import org.ferhat.project_management_app.core.result.Result;
import org.ferhat.project_management_app.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Message.CREATED, "201", data);
    }

    public static <T> ResultData<T> deleted(T data){
        return new ResultData<>(true, Message.DELETED, "204", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Message.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Message.OK, "200", data);
    }

    public static Result ok() {
        return new Result(true, Message.OK, "200");
    }

    public static Result resultNotFoundError(String message) {
        return new Result(false, message, "404");
    }

}
