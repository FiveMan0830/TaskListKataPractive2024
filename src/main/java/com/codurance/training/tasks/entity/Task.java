package com.codurance.training.tasks.entity;

public final class Task implements Comparable<Task> {
    private final TaskId id;
    private final TaskDescription description;
    private TaskStatus status;

    public Task(TaskId id, TaskDescription description, TaskStatus done) {
        this.id = id;
        this.description = description;
        this.status = done;
    }

    public TaskId getId() {
        return id;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    @Override
    public int compareTo(Task o) {
        return Long.compare(id.getId(), o.getId().getId());
    }
}
