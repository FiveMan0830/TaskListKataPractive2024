package com.codurance.training.tasks.adapter.port.out;

public class TaskPO {
    private final String id;
    private final String projectName;
    private final String description;
    private final boolean check;
    private final String dueDate;

    public TaskPO(String id, String projectName, String description, boolean check, String dueDate) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.check = check;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCheck() {
        return check;
    }

    public String getDueDate() {
        return dueDate;
    }
}
