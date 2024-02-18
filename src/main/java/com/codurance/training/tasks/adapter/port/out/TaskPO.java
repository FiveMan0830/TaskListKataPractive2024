package com.codurance.training.tasks.adapter.port.out;

public class TaskPO {
    private final String id;
    private final String description;
    private final boolean check;

    public TaskPO(String id, String description, boolean check) {
        this.id = id;
        this.description = description;
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCheck() {
        return check;
    }
}
