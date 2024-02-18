package com.codurance.training.tasks.usecase.port.in.task.add;

import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.IdNotAllowedException;
import com.codurance.training.tasks.usecase.ProjectNotFoundException;
import com.codurance.training.tasks.usecase.port.out.ProjectDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class AddTaskUseCase {
    private final ProjectRepository projectRepository;

    public AddTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String projectName, String description, String id) {
        if(!projectRepository.findProject(projectName).isPresent())
            throw new ProjectNotFoundException(format(ProjectNotFoundException.PROJECT_NOT_FOUND, projectName));
        Pattern regex = Pattern.compile("[^A-Za-z0-9]");
        if(regex.matcher(id).matches())
            throw new IdNotAllowedException();

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