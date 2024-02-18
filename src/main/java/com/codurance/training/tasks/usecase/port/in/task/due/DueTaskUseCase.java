package com.codurance.training.tasks.usecase.port.in.task.due;

import com.codurance.training.tasks.entity.DueDate;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.UseCaseFailureException;
import com.codurance.training.tasks.usecase.port.out.TaskDataMapper;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class DueTaskUseCase {
    private static String DATE_PATTERN = "\\b\\d{4}-\\d{2}-\\d{2}\\b";
    private final ProjectRepository projectRepository;

    public DueTaskUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(String taskId, String dueDate) {
        if (!projectRepository.findTask(taskId).isPresent())
            throw new UseCaseFailureException(format(UseCaseFailureException.TASK_NOT_FOUND, taskId));


        Pattern pattern = Pattern.compile(DATE_PATTERN);
        if(!pattern.matcher(dueDate).matches())
            throw new UseCaseFailureException(format(UseCaseFailureException.DATE_FORMAT_NOT_ALLOWED, dueDate));

        Task task = TaskDataMapper.toDomain(projectRepository.findTask(taskId).get());
        task.setDueDate(DueDate.of(dueDate));

        projectRepository.save(TaskDataMapper.toData(task));
    }
}
