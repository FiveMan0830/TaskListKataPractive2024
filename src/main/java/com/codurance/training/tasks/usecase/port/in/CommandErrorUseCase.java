package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Command;
import com.codurance.training.tasks.usecase.TaskListRunner;

public class CommandErrorUseCase {
    private final TaskListRunner taskListRunner;

    public CommandErrorUseCase(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }//command

    public void error(Command command) {
        taskListRunner.getOut().printf("I don't know what the command \"%s\" is.", command.getCommand());
        taskListRunner.getOut().println();
    }
}