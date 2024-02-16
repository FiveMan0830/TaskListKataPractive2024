package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.ProjectName;

public class ProjectNameData {
    private final String name;

    public ProjectNameData(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

    public static ProjectNameData of(String str) {
        return new ProjectNameData(str);
    }
}
