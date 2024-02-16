package com.codurance.training.tasks.framework;

import com.codurance.training.tasks.usecase.TaskListRunner;

import java.io.IOException;

public class ThreadRunner implements Runnable {
    private static final String QUIT = "quit";

    private final TaskListRunner taskListRunner;

    public ThreadRunner(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }

    // Thread
    public void run() {
        while (true) {
            taskListRunner.getOut().print("> ");
            taskListRunner.getOut().flush();
            String command;
            try {
                command = taskListRunner.getIn().readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            taskListRunner.execute(command);
        }
    }
}