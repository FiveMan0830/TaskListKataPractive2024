package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

public class AddTaskUseCase {
    private final ProjectRepository projectRepository;

    public AddTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String projectName, String description, long id) {
        Project project = projectRepository.find(projectName).get();
        project.add(new Task(id, description, false));
        projectRepository.save(project);
    }
}