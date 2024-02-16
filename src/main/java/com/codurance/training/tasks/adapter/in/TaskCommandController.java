package com.codurance.training.tasks.adapter.in;

import com.codurance.training.tasks.entity.Command;
import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.in.*;
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
    private final ShowTaskUseCase showTaskUseCase;
    private final UncheckTaskUseCase uncheckTaskUseCase;
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;
    public TaskCommandController(ProjectRepository projectRepository, BufferedReader in, PrintWriter out) {
        addProjectUseCase = new AddProjectUseCase(projectRepository);
        addTaskUseCase = new AddTaskUseCase(projectRepository);
        showTaskUseCase = new ShowTaskUseCase(projectRepository);
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

    private void add(Command command) {
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProjectUseCase.execute(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTaskUseCase.execute(projectTask[0], projectTask[1], getLastId());
        }
    }
//
//    private void addTask(String project, String description) {
//        List<Task> projectTasks = taskListRunner.getTasks().get(project);
//        if (projectTasks == null) {
//            taskListRunner.getOut().printf("Could not find a project with the name \"%s\".", project);
//            taskListRunner.getOut().println();
//            return;
//        }
//        projectTasks.add(new Task(taskListRunner.getLastId(), description, false));
//    }

//    public void setDone(String idString, boolean done) {
//        int id = Integer.parseInt(idString);
//        for (Map.Entry<String, List<Task>> project : taskListRunner.getTasks().entrySet()) {
//            for (Task task : project.getValue()) {
//                if (task.getId() == id) {
//                    task.setDone(done);
//                    return;
//                }
//            }
//        }
//        taskListRunner.getOut().printf("Could not find a task with an ID of %d.", id);
//        taskListRunner.getOut().println();
//    }

    private void show() {
        List<Project> projectList = showTaskUseCase.execute();
        for (Project project : projectList) {
            out.println(project.getProjectName());
            for (Task task : project.getProjectTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void error(Command command) {
        out.printf("I don't know what the command \"%s\" is.", command.getCommand());
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
                add(Command.of(commandRest[1]));
                break;
            case "check":
                checkTaskUseCase.execute(commandRest[1]);
                break;
            case "uncheck":
                uncheckTaskUseCase.execute(commandRest[1]);
                break;
            case "help":
                help();
                break;
            default:
                error(Command.of(command));
                break;
        }
    }
}