package com.codurance.training.tasks.usecase;

public class UseCaseFailureException extends RuntimeException {
    public static final String PROJECT_NOT_FOUND = "Could not find a project with the name \"%s\".";
    public static final String ID_NOT_ALLOWED = "Id with special characters is not allowed: \"%s\".";
    public static final String TASK_NOT_FOUND = "Could not find a task with an ID of \"%s\".";
    public static final String DATE_FORMAT_NOT_ALLOWED = "Date format not allowed: \"%s\".";
    public UseCaseFailureException() {
    }

    public UseCaseFailureException(String message) {
        super(message);
    }

    public UseCaseFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UseCaseFailureException(Exception e) {
        super(e);
    }
}
