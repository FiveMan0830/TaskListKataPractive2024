package com.codurance.training.tasks.usecase.port.in.task.add;

import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.ProjectNotFoundException;
import com.codurance.training.tasks.usecase.port.out.ProjectDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import static java.lang.String.format;

public class AddTaskUseCase {
    private final ProjectRepository projectRepository;

    public AddTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String projectName, String description, long id) {
        if(!projectRepository.find(projectName).isPresent())
            throw new ProjectNotFoundException(format(ProjectNotFoundException.PROJECT_NOT_FOUND, projectName));

        Project project = ProjectDataMapper.toDomain(projectRepository.find(projectName).get());

        Task task = new Task(
                new TaskId(id),
                new TaskDescription(description),
                TaskStatus.Unchecked
        );
        project.add(task);

        projectRepository.save(ProjectDataMapper.toData(project));
    }
}