package com.codurance.training.tasks.adapter;

import com.codurance.training.tasks.entity.Command;
import com.codurance.training.tasks.usecase.TaskListRunner;

public class TaskCommandController {
    private final TaskListRunner taskListRunner;

    public TaskCommandController(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskListRunner.getShowTaskUseCase().show();
                break;
            case "add":
                taskListRunner.getAddTaskUseCase().add(Command.of(commandRest[1]));
                break;
            case "check":
                taskListRunner.getCheckTaskUseCase().check(Command.of(commandRest[1]));
                break;
            case "uncheck":
                taskListRunner.getCheckTaskUseCase().uncheck(Command.of(commandRest[1]));
                break;
            case "help":
                taskListRunner.getHelpPromptUseCase().help();
                break;
            default:
                taskListRunner.getCommandErrorUseCase().error(Command.of(command));
                break;
        }
    }
}