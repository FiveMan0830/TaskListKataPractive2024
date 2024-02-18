package com.codurance.training.tasks.usecase.port.in.task.check;


import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskStatus;
import com.codurance.training.tasks.usecase.UseCaseFailureException;
import com.codurance.training.tasks.usecase.port.out.TaskDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import static java.lang.String.format;

public class CheckTaskUseCase {
    private final ProjectRepository projectRepository;

    public CheckTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String idString) {
        if (!projectRepository.findTask(idString).isPresent())
            throw new UseCaseFailureException(format(UseCaseFailureException.TASK_NOT_FOUND, idString));

        Task task = TaskDataMapper.toDomain(projectRepository.findTask(idString).get());
        task.setStatus(TaskStatus.Checked);

        projectRepository.save(TaskDataMapper.toData(task));
    }
}