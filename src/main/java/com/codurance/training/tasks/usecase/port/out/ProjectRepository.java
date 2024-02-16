package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void save(Project project);
    void save(Task task);
    Optional<Project> find(String projectName);
    Optional<Task> find(int id);
    List<Project> findAll();
}
