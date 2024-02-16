package com.codurance.training.tasks.usecase.port.in.project.add;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.usecase.port.out.ProjectDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

public class AddProjectUseCase {
    private final ProjectRepository projectRepository;

    public AddProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String name) {
        Project project = new Project(new ProjectName(name));

        projectRepository.save(ProjectDataMapper.toData(project));
    }
}