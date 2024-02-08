package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.Command;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class TaskListRunner implements Runnable {
    private static final String QUIT = "quit";

    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    // Main Function
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskListRunner(in, out).run();
    }

    public TaskListRunner(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    // Thread
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
                check(Command.of(commandRest[1]));
                break;
            case "uncheck":
                uncheck(Command.of(commandRest[1]));
                break;
            case "help":
                help();
                break;
            default:
                error(Command.of(command));
                break;
        }
    }

    // command
    private void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    // command
    private void add(Command command) {
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
        tasks.put(name, new ArrayList<>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    // command
    private void check(Command command) {
        setDone(command.getCommand(), true);
    }

    //command
    private void uncheck(Command command) {
        setDone(command.getCommand(), false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    //command
    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    //command
    private void error(Command command) {
        out.printf("I don't know what the command \"%s\" is.", command.getCommand());
        out.println();
    }

    private long nextId() {
        return ++lastId;
    }
}
