package com.codurance.training.tasks.framework;

import com.codurance.training.tasks.adapter.port.QuitApplicationException;
import com.codurance.training.tasks.adapter.port.in.TaskCommandController;
import com.codurance.training.tasks.adapter.port.in.TaskListInput;
import com.codurance.training.tasks.adapter.port.out.TaskListOutput;

public class TaskListApplication implements Runnable {
    private final TaskCommandController controller;
    private final TaskListInput in;
    private final TaskListOutput out;

    public TaskListApplication(TaskCommandController controller, TaskListInput in, TaskListOutput out) {
        this.controller = controller;
        this.in = in;
        this.out = out;
    }

    public void run() {
        while (true) {
            out.greet();
            String command = in.readLine();
            try {
                String response = controller.execute(command);
                out.response(response);
            } catch (QuitApplicationException e) {
                return;
            }
        }
    }
}
