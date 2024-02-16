package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.usecase.TaskListRunner;

public class HelpPromptUseCase {
    private final TaskListRunner taskListRunner;

    public HelpPromptUseCase(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }//command

    public void help() {
        taskListRunner.getOut().println("Commands:");
        taskListRunner.getOut().println("  show");
        taskListRunner.getOut().println("  add project <project name>");
        taskListRunner.getOut().println("  add task <project name> <task description>");
        taskListRunner.getOut().println("  check <task ID>");
        taskListRunner.getOut().println("  uncheck <task ID>");
        taskListRunner.getOut().println();
    }
}