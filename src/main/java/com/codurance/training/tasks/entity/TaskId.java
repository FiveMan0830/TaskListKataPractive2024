package com.codurance.training.tasks.entity;

public class TaskId {
    private final long id;

    public TaskId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static TaskId of(int id) {
        return new TaskId(id);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof TaskId)) return false;
        TaskId t = (TaskId) o;
        return id == t.getId();
    }
}
