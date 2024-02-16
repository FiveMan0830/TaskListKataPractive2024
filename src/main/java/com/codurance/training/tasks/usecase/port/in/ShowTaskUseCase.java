package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

import java.util.List;

public class ShowTaskUseCase {
    private final ProjectRepository projectRepository;

    public ShowTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> execute() {
        return projectRepository.findAll();
    }
}