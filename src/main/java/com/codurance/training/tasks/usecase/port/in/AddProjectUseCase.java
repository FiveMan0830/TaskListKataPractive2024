package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

public class AddProjectUseCase {
    private final ProjectRepository projectRepository;

    public AddProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String name) {
        projectRepository.save(new Project(name));
    }
}