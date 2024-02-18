package com.codurance.training.tasks.framework.io;

import com.codurance.training.tasks.adapter.port.out.TaskListOutput;

import java.io.PrintWriter;

public class TaskListCommandPrinter implements TaskListOutput {
    private final PrintWriter pw;

    public TaskListCommandPrinter(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void greet() {
        pw.print("> ");
        pw.flush();
    }

    @Override
    public void response(String str) {
        pw.print(str);
    }
}
