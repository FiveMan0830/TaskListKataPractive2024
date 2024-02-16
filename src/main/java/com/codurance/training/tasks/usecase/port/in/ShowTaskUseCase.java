package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.TaskListRunner;

import java.util.List;
import java.util.Map;

public class ShowTaskUseCase {
    private final TaskListRunner taskListRunner;

    public ShowTaskUseCase(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }// command

    public void show() {
        for (Map.Entry<String, List<Task>> project : taskListRunner.getTasks().entrySet()) {
            taskListRunner.getOut().println(project.getKey());
            for (Task task : project.getValue()) {
                taskListRunner.getOut().printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            taskListRunner.getOut().println();
        }
    }
}