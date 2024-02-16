package com.codurance.training.tasks.entity;

public class TaskDescription {
    private final String description;

    public TaskDescription(String description) {
        this.description = description;
    }

    public String value() {
        return description;
    }

    public static TaskDescription of(String description) {
        return new TaskDescription(description);
    }
}
