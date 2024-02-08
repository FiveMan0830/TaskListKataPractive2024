package com.codurance.training.tasks.entity;

import java.util.List;

public class Project {
    private String projectName;
    private List<Task> projectTasks;

    public Project(String projectName, List<Task> projectTasks) {
        this.projectName = projectName;
        this.projectTasks = projectTasks;
    }

    public String getProjectName() {
        return projectName;
    }

    public List<Task> getProjectTasks() {
        return projectTasks;
    }
}
