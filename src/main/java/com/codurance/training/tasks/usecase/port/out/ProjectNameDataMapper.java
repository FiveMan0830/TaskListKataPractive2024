package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.ProjectName;

public class ProjectNameDataMapper {
    public static ProjectNameData toData(ProjectName projectName) {
        return new ProjectNameData(projectName.getName());
    }

    public static ProjectName toDomain(ProjectNameData data) {
        return new ProjectName(data.value());
    }
}
