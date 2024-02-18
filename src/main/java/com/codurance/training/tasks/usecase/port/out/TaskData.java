package com.codurance.training.tasks.usecase.port.out;

import static java.lang.String.format;

public final class TaskData implements Comparable<TaskData> {
    private final TaskIdData id;
    private final TaskDescriptionData description;
    private TaskStatusData status;

    public TaskData(TaskIdData id, TaskDescriptionData description, TaskStatusData done) {
        this.id = id;
        this.description = description;
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


    @Override
    public int compareTo(TaskData o) {
        return Long.compare(id.value(), o.getId().value());
    }

    @Override
    public String toString() {
        return format("    [%c] %d: %s%n", (status.equals(TaskStatusData.Checked) ? 'x' : ' '), id.value(), description.value());
    }
}
