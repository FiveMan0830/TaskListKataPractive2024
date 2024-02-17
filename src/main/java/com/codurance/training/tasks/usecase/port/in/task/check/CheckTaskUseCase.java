package com.codurance.training.tasks.usecase.port.in.task.check;


import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskStatus;
import com.codurance.training.tasks.usecase.TaskNotFoundException;
import com.codurance.training.tasks.usecase.port.out.TaskDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import static java.lang.String.format;

public class CheckTaskUseCase {
    private final ProjectRepository projectRepository;

    public CheckTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String idString) {
        int id = Integer.parseInt(idString);
        if (!projectRepository.find(id).isPresent())
            throw new TaskNotFoundException(format(TaskNotFoundException.TASK_NOT_FOUND, id));

        Task task = TaskDataMapper.toDomain(projectRepository.find(id).get());
        task.setStatus(TaskStatus.Checked);

        projectRepository.save(TaskDataMapper.toData(task));
    }
}