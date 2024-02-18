package com.codurance.training.tasks.entity;

public class TaskId {
    private final String id;

    public TaskId(String id) {
        this.id = id;
    }

    public String value() {
        return id;
    }

    public static TaskId of(String id) {
        return new TaskId(id);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof TaskId)) return false;
        TaskId t = (TaskId) o;
        return id.equals(t.value());
    }
}
