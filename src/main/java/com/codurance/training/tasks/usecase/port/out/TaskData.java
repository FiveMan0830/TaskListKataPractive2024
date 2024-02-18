package com.codurance.training.tasks.usecase.port.out;

import static java.lang.String.format;

public final class TaskData implements Comparable<TaskData> {
    private final TaskIdData id;
    private final TaskDescriptionData description;
    private final DueDateData dueDate;
    private TaskStatusData status;

    public TaskData(TaskIdData id, TaskDescriptionData description, DueDateData dueDate, TaskStatusData done) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.status = done;
    }

    public TaskIdData getId() {
        return id;
    }

    public TaskDescriptionData getDescription() {
        return description;
    }

    public TaskStatusData getStatus() {
        return status;
    }

    public DueDateData getDueDate() {
        return dueDate;
    }

    @Override
    public int compareTo(TaskData o) {
        return id.value().compareTo(o.getId().value());
    }

    @Override
    public String toString() {
        return format("    [%c] %s: %s%n", (status.equals(TaskStatusData.Checked) ? 'x' : ' '), id.value(), description.value());
    }
}
