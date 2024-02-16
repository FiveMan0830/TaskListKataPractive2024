package com.codurance.training.tasks.usecase.port.in;

import com.codurance.training.tasks.entity.Command;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.TaskListRunner;

import java.util.ArrayList;
import java.util.List;

public class AddTaskUseCase {
    private final TaskListRunner taskListRunner;

    public AddTaskUseCase(TaskListRunner taskListRunner) {
        this.taskListRunner = taskListRunner;
    }// command

    public void add(Command command) {
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        taskListRunner.getTasks().put(name, new ArrayList<>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = taskListRunner.getTasks().get(project);
        if (projectTasks == null) {
            taskListRunner.getOut().printf("Could not find a project with the name \"%s\".", project);
            taskListRunner.getOut().println();
            return;
        }
        projectTasks.add(new Task(taskListRunner.getLastId(), description, false));
    }
}