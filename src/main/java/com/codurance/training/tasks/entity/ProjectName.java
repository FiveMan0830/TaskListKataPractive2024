package com.codurance.training.tasks.entity;

public class ProjectName {
    private final String name;

    public ProjectName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProjectName of(String str) {
        return new ProjectName(str);
    }
}
