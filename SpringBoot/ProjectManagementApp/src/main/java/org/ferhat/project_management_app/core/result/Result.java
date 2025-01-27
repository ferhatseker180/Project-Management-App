package org.ferhat.project_management_app.core.result;

import lombok.Getter;

public class Result {

    private boolean status;
    private String message;
    private String code;

    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
