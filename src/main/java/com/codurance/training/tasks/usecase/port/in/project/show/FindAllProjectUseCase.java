package com.codurance.training.tasks.usecase.port.in.project.show;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

import java.util.List;

public class FindAllProjectUseCase {
    private final ProjectRepository projectRepository;

    public FindAllProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> execute() {
        return projectRepository.findAll();
    }
}