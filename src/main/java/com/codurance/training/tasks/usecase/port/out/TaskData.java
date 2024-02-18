package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.ProjectName;

import static java.lang.String.format;

public final class TaskData implements Comparable<TaskData> {
    private final TaskIdData id;
    private final ProjectNameData project;
    private final DueDateData dueDate;
    private final TaskDescriptionData description;
    private TaskStatusData status;

    public TaskData(TaskIdData id, ProjectNameData project, TaskDescriptionData description, DueDateData dueDate, TaskStatusData done) {
        this.id = id;
        this.project = project;
        this.description = description;
        this.dueDate = dueDate;
        this.status = done;
    }

    public TaskIdData getId() {
        return id;
    }

    public ProjectNameData getProject() {
        return project;
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
