package com.codurance.training.tasks.adapter.port.in;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskStatus;
import com.codurance.training.tasks.usecase.ProjectNotFoundException;
import com.codurance.training.tasks.usecase.TaskNotFoundException;
import com.codurance.training.tasks.usecase.port.in.project.add.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.project.show.FindAllProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.task.add.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.check.CheckTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.check.UncheckTaskUseCase;
import com.codurance.training.tasks.usecase.port.out.ProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TaskCommandController implements Runnable {
    private static final String QUIT = "quit";
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final CheckTaskUseCase checkTaskUseCase;
    private final FindAllProjectUseCase findAllProjectUseCase;
    private final UncheckTaskUseCase uncheckTaskUseCase;
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;
    public TaskCommandController(ProjectRepository projectRepository, BufferedReader in, PrintWriter out) {
        addProjectUseCase = new AddProjectUseCase(projectRepository);
        addTaskUseCase = new AddTaskUseCase(projectRepository);
        findAllProjectUseCase = new FindAllProjectUseCase(projectRepository);
        checkTaskUseCase = new CheckTaskUseCase(projectRepository);
        uncheckTaskUseCase = new UncheckTaskUseCase(projectRepository);
        this.in = in;
        this.out = out;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void add(String command){
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProjectUseCase.execute(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            try {
                String[] projectTask = subcommandRest[1].split(" ", 2);
                addTaskUseCase.execute(projectTask[0], projectTask[1], getLastId());
            } catch (ProjectNotFoundException e) {
                out.println(e.getMessage());
            }
        }
    }

    private void show() {
        List<Project> projectList = findAllProjectUseCase.execute();
        for (Project project : projectList) {
            out.println(project.getProjectName().getName());
            for (Task task : project.getProjectTasks()) {
                out.printf("    [%c] %d: %s%n", (task.getStatus().equals(TaskStatus.Checked) ? 'x' : ' '), task.getId().getId(), task.getDescription().getDescription());
            }
            out.println();
        }
    }

    private void check(String command) {
        try {
            checkTaskUseCase.execute(command);
        } catch (TaskNotFoundException e) {
            out.println(e.getMessage());
        }
    }

    private void uncheck(String command) {
        try {
            uncheckTaskUseCase.execute(command);
        } catch (TaskNotFoundException e) {
            out.println(e.getMessage());
        }
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private long getLastId() {
        return ++lastId;
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }
}