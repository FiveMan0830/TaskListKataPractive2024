package com.codurance.training.tasks.usecase.port.in.task.delete;

import com.codurance.training.tasks.usecase.UseCaseFailureException;
import com.codurance.training.tasks.usecase.port.out.TaskIdData;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import static java.lang.String.format;

public class DeleteTaskUseCase {
    private final ProjectRepository projectRepository;

    public DeleteTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String taskId) {
        if (!projectRepository.findTask(taskId).isPresent())
            throw new UseCaseFailureException(format(UseCaseFailureException.TASK_NOT_FOUND, taskId));

        projectRepository.delete(TaskIdData.of(taskId));
    }
}
