package com.codurance.training.tasks.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Project {
    private String projectName;
    private Set<Task> projectTasks;

    public Project(String projectName) {
        this.projectName = projectName;
        this.projectTasks = new HashSet<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public List<Task> getProjectTasks() {
        return projectTasks.stream().sorted().collect(Collectors.toList());
    }

    public void add(Task task) {
        projectTasks.add(task);
    }
}
