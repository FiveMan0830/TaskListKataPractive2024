package com.codurance.training.tasks.framework.io;

import com.codurance.training.tasks.adapter.port.in.TaskListInput;

import java.io.BufferedReader;
import java.io.IOException;

public class TaskListCommandReader implements TaskListInput {
    private final BufferedReader in;

    public TaskListCommandReader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public String readLine() {
        String str = "";
        try {
             str = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
