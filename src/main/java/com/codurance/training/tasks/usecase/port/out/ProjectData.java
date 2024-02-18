package com.codurance.training.tasks.usecase.port.out;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectData {
    private final ProjectNameData projectName;
    private final Set<TaskData> projectTasks;

    public ProjectData(ProjectNameData projectName) {
        this.projectName = projectName;
        this.projectTasks = new HashSet<>();
    }

    public ProjectData(ProjectNameData projectName, Set<TaskData> projectTasks) {
        this.projectName = projectName;
        this.projectTasks = projectTasks;
    }

    public ProjectNameData getProjectName() {
        return projectName;
    }

    public List<TaskData> getProjectTasks() {
        return projectTasks.stream().sorted().collect(Collectors.toList());
    }

    public void add(TaskData task) {
        projectTasks.add(task);
    }

    @Override
    public String toString() {
        return projectName.value();
    }
}
