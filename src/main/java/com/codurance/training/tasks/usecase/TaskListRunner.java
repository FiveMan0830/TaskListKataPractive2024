package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.adapter.TaskCommandController;
import com.codurance.training.tasks.entity.TaskList;
import com.codurance.training.tasks.framework.ThreadRunner;
import com.codurance.training.tasks.usecase.port.in.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class TaskListRunner implements Runnable {
    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final BufferedReader in;
    private final PrintWriter out;
    private final ThreadRunner threadRunner = new ThreadRunner(this);
    private final ShowTaskUseCase showTaskUseCase = new ShowTaskUseCase(this);
    private final AddTaskUseCase addTaskUseCase = new AddTaskUseCase(this);
    private final CheckTaskUseCase checkTaskUseCase = new CheckTaskUseCase(this);
    private final HelpPromptUseCase helpPromptUseCase = new HelpPromptUseCase(this);
    private final CommandErrorUseCase commandErrorUseCase = new CommandErrorUseCase(this);
    private final TaskCommandController taskCommandController = new TaskCommandController(this);

    private long lastId = 0;

    public TaskListRunner(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public long getLastId() {
        return ++lastId;
    }

    public ShowTaskUseCase getShowTaskUseCase() {
        return showTaskUseCase;
    }

    public AddTaskUseCase getAddTaskUseCase() {
        return addTaskUseCase;
    }

    public CheckTaskUseCase getCheckTaskUseCase() {
        return checkTaskUseCase;
    }

    public HelpPromptUseCase getHelpPromptUseCase() {
        return helpPromptUseCase;
    }

    public CommandErrorUseCase getCommandErrorUseCase() {
        return commandErrorUseCase;
    }

    // Thread
    public void run() {
        threadRunner.run();
    }

    public void execute(String commandLine) {
        taskCommandController.execute(commandLine);
    }
}
