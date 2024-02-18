package com.codurance.training.tasks.adapter.port.in;

import com.codurance.training.tasks.adapter.port.QuitApplicationException;
import com.codurance.training.tasks.usecase.ProjectNotFoundException;
import com.codurance.training.tasks.usecase.TaskNotFoundException;
import com.codurance.training.tasks.usecase.port.in.project.add.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.task.add.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.check.CheckTaskUseCase;
import com.codurance.training.tasks.usecase.port.in.task.uncheck.UncheckTaskUseCase;
import com.codurance.training.tasks.usecase.port.out.ProjectData;
import com.codurance.training.tasks.usecase.port.out.TaskData;
import com.codurance.training.tasks.usecase.port.out.repository.ProjectRepository;

import java.util.List;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

public class TaskCommandController {
    private static final String QUIT = "quit";
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final CheckTaskUseCase checkTaskUseCase;
    private final UncheckTaskUseCase uncheckTaskUseCase;
    private final ProjectRepository projectRepository;

    private long lastId = 0;
    public TaskCommandController(ProjectRepository projectRepository) {
        addProjectUseCase = new AddProjectUseCase(projectRepository);
        addTaskUseCase = new AddTaskUseCase(projectRepository);
        checkTaskUseCase = new CheckTaskUseCase(projectRepository);
        uncheckTaskUseCase = new UncheckTaskUseCase(projectRepository);
        this.projectRepository = projectRepository;
    }

    private String add(String command){
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProjectUseCase.execute(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            try {
                String[] projectTask = subcommandRest[1].split(" ", 2);
                addTaskUseCase.execute(projectTask[0], projectTask[1], getLastId());
            } catch (ProjectNotFoundException e) {
                return e.getMessage();
            }
        }
        return "";
    }

    private String show() {
        StringBuilder sb = new StringBuilder();
        List<ProjectData> projectList = projectRepository.findAll();
        for (ProjectData project : projectList) {
            sb.append(project.toString())
                    .append(lineSeparator());
            List<TaskData> taskDataList = project.getProjectTasks();
            for (TaskData task : taskDataList) {
                sb.append(task.toString());
            }
            sb.append(lineSeparator());
        }
        return sb.toString();
    }

    private String check(String command) {
        try {
            checkTaskUseCase.execute(command);
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
        return "";
    }

    private String uncheck(String command) {
        try {
            uncheckTaskUseCase.execute(command);
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
        return "";
    }

    private String error(String command) {
        StringBuilder sb = new StringBuilder();
        sb.append(format("I don't know what the command \"%s\" is.", command))
                .append(lineSeparator())
                .append(lineSeparator())
                .append(lineSeparator());
        return sb.toString();
    }

    private String help() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commands:")
          .append(lineSeparator())
          .append("  show")
          .append(lineSeparator())
          .append("  add project <project name>")
          .append(lineSeparator())
          .append("  add task <project name> <task description>")
          .append(lineSeparator())
          .append("  check <task ID>")
          .append(lineSeparator())
          .append("  uncheck <task ID>")
          .append(lineSeparator())
          .append(lineSeparator());
        return sb.toString();
    }

    private long getLastId() {
        return ++lastId;
    }

    public String execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                return show();
            case "add":
                return add(commandRest[1]);
            case "check":
                return check(commandRest[1]);
            case "uncheck":
                return uncheck(commandRest[1]);
            case "help":
                return help();
            case "quit":
                throw new QuitApplicationException();
            default:
                return error(command);
        }
    }
}