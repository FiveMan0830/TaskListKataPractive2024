package com.codurance.training.tasks.framework;

import com.codurance.training.tasks.adapter.port.in.TaskCommandController;
import com.codurance.training.tasks.adapter.port.out.InMemoryProjectRepository;
import com.codurance.training.tasks.adapter.port.in.TaskListInput;
import com.codurance.training.tasks.adapter.port.out.TaskListOutput;
import com.codurance.training.tasks.framework.io.TaskListCommandPrinter;
import com.codurance.training.tasks.framework.io.TaskListCommandReader;
import com.codurance.training.tasks.framework.persistant.ProjectMemoryStore;
import com.codurance.training.tasks.framework.persistant.TaskMemoryStore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaskListMain {
    // Main Function
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        TaskListOutput taskListOutput = new TaskListCommandPrinter(out);
        TaskListInput taskListInput = new TaskListCommandReader(in);
        InMemoryProjectRepository repository = new InMemoryProjectRepository(
                new ProjectMemoryStore(new HashMap<>()),
                new TaskMemoryStore(new HashMap<>())
        );
        TaskCommandController controller = new TaskCommandController(repository);

        TaskListApplication application = new TaskListApplication(controller, taskListInput, taskListOutput);
        application.run();
    }
}