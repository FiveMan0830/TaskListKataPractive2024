package com.codurance.training.tasks.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Project {
    private final ProjectName projectName;
    private final Set<Task> projectTasks;

    public Project(ProjectName projectName) {
        this.projectName = projectName;
        this.projectTasks = new HashSet<>();
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public List<Task> getProjectTasks() {
        return projectTasks.stream().sorted().collect(Collectors.toList());
    }

    public void add(Task task) {
        projectTasks.add(task);
    }
}
