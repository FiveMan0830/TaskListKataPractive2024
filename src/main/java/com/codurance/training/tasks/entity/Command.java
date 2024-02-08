package com.codurance.training.tasks.entity;

public class Command {
    private final String command;

    public static Command of(String str) {
        return new Command(str);
    }

    public Command(String command) {
        this.command = command;
    }

    public String[] split(String regex, int limit) {
        return command.split(regex, limit);
    }

    public String getCommand() {
        return command;
    }
}
