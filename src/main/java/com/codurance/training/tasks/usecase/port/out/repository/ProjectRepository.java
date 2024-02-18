package com.codurance.training.tasks.usecase.port.out.repository;

import com.codurance.training.tasks.usecase.port.out.ProjectData;
import com.codurance.training.tasks.usecase.port.out.TaskData;
import com.codurance.training.tasks.usecase.port.out.TaskIdData;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void save(ProjectData project);
    void save(TaskData task);
    Optional<ProjectData> findProject(String projectName);
    Optional<TaskData> findTask(String id);
    List<ProjectData> findAll();
    void delete(TaskIdData taskId);
}
