package com.codurance.training.tasks.usecase;

public class TaskNotFoundException extends RuntimeException {
    public static final String TASK_NOT_FOUND = "Could not find a task with an ID of %s.";

    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskNotFoundException(Exception e) {
        super(e);
    }
}
