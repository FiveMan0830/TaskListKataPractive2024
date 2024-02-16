package com.codurance.training.tasks.usecase.port.out;

public class TaskDescriptionData {
    private final String description;

    public TaskDescriptionData(String description) {
        this.description = description;
    }

    public String value() {
        return description;
    }

    public static TaskDescriptionData of(String description) {
        return new TaskDescriptionData(description);
    }
}
