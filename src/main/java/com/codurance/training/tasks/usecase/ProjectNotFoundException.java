package com.codurance.training.tasks.usecase;

public class ProjectNotFoundException extends RuntimeException {
    public static final String PROJECT_NOT_FOUND = "Could not find a project with the name \"%s\".";

    public ProjectNotFoundException() {
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectNotFoundException(Exception e) {
        super(e);
    }
}
