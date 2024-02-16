package com.codurance.training.tasks.framework;

import com.codurance.training.tasks.adapter.port.in.TaskCommandController;
import com.codurance.training.tasks.adapter.port.out.InMemoryProjectRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskListMain {// Main Function

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskCommandController(new InMemoryProjectRepository(), in, out).run();
    }
}