package com.codurance.training.tasks.usecase.port.out.repository;

import com.codurance.training.tasks.usecase.port.out.ProjectData;
import com.codurance.training.tasks.usecase.port.out.TaskData;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void save(ProjectData project);
    void save(TaskData task);
    Optional<ProjectData> find(String projectName);
    Optional<TaskData> find(int id);
    List<ProjectData> findAll();
}