package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProjectDataMapper {
    public static ProjectData toData(Project project) {
        return new ProjectData(
                ProjectNameDataMapper.toData(project.getProjectName()),
                new HashSet<>(TaskDataMapper.toData(project.getProjectTasks()))
        );
    }

    public static Project toDomain(ProjectData data) {
        return new Project(
                ProjectNameDataMapper.toDomain(data.getProjectName()),
                new HashSet<>(TaskDataMapper.toDomain(data.getProjectTasks()))
        );
    }

    public static List<ProjectData> toData(List<Project> projects) {
        List<ProjectData> dataList = new ArrayList<>();

        for(Project project : projects) {
            dataList.add(toData(project));
        }

        return dataList;
    }

    public static List<Project> toDomain(List<ProjectData> dataList) {
        List<Project> projects = new ArrayList<>();

        for(ProjectData data : dataList) {
            projects.add(toDomain(data));
        }

        return projects;
    }
}
