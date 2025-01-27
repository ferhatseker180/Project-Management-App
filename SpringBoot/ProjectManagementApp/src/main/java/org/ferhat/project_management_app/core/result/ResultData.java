package org.ferhat.project_management_app.core.result;

import lombok.Getter;

public class ResultData<T> extends Result {

    private T data;

    public ResultData(boolean status, String message, String code, T data) {
        super(status, message, code);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
