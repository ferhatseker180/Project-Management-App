package org.ferhat.project_management_app.core.exceptions;

public class NotFoundExceptions extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
