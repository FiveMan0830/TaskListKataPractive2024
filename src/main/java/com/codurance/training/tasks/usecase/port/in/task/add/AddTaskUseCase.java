package com.codurance.training.tasks.usecase.port.in.task.add;

import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.UseCaseFailureException;
import com.codurance.training.tasks.usecase.port.out.ProjectDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class AddTaskUseCase {
    private final ProjectRepository projectRepository;

    public AddTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String projectName, String description, String id) {
        if(!projectRepository.findProject(projectName).isPresent())
            throw new UseCaseFailureException(format(UseCaseFailureException.PROJECT_NOT_FOUND, projectName));
        Pattern regex = Pattern.compile("[^A-Za-z0-9]");
        if(regex.matcher(id).matches())
            throw new UseCaseFailureException();

        Project project = ProjectDataMapper.toDomain(projectRepository.findProject(projectName).get());

        Task task = new Task(
                new TaskId(id),
                new TaskDescription(description),
                TaskStatus.Unchecked
        );
        project.add(task);

        projectRepository.save(ProjectDataMapper.toData(project));
    }
}