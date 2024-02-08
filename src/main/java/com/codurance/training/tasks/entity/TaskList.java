package com.codurance.training.tasks.entity;

import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    private List<Project> projects;

    public TaskList(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Set<Map.Entry<String, List<Task>>> entrySet() {
        return projects.stream()
                .map(x -> new AbstractMap.SimpleEntry<String, List<Task>>(
                        x.getProjectName(),
                        x.getProjectTasks()))
                .collect(Collectors.toSet());
    }

    public void put(String name, List<Task> tasks) {
        projects.add(new Project(name, tasks));
    }

    public List<Task> get(String name) {
        return projects.stream().filter(x -> x.getProjectName().equals(name)).findFirst().get().getProjectTasks();
    }
}
