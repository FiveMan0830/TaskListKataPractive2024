package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Command;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.TaskListRunner;

import java.util.List;
import java.util.Map;

public class CheckTaskUseCase {
    private final TaskListRunner taskListRunner;

    public CheckTaskUseCase(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }// command

    public void check(Command command) {
        setDone(command.getCommand(), true);
    }//command

    public void uncheck(Command command) {
        setDone(command.getCommand(), false);
    }

    public void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : taskListRunner.getTasks().entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        taskListRunner.getOut().printf("Could not find a task with an ID of %d.", id);
        taskListRunner.getOut().println();
    }
}