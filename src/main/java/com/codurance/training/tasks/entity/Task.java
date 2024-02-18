package com.codurance.training.tasks.entity;

public final class Task implements Comparable<Task> {
    private final TaskId id;
    private final ProjectName project;
    private final TaskDescription description;
    private TaskStatus status;
    private DueDate dueDate;

    public Task(TaskId id, ProjectName project, TaskDescription description, TaskStatus done) {
        this.id = id;
        this.project = project;
        this.description = description;
        this.status = done;
        dueDate = new DueDate("");
    }

    public TaskId getId() {
        return id;
    }

    public ProjectName getProject() {
        return project;
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

    public DueDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean hasDueDate() {
        return dueDate.value().equals("");
    }

    @Override
    public int compareTo(Task o) {
        return id.value().compareTo(o.getId().value());
    }
}
