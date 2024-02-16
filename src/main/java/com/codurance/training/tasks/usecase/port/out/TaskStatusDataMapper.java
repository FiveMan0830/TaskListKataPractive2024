package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.TaskStatus;

public class TaskStatusDataMapper {
    public static TaskStatusData toData(TaskStatus status) {
        TaskStatusData data = TaskStatusData.Unchecked;
        switch (status){
            case Checked:
                data = TaskStatusData.Checked;
                break;
            case Unchecked:
                data = TaskStatusData.Unchecked;
                break;
        }
        return data;
    }

    public static TaskStatus toDomain(TaskStatusData status) {
        TaskStatus data = TaskStatus.Unchecked;
        switch (status){
            case Checked:
                data = TaskStatus.Checked;
                break;
            case Unchecked:
                data = TaskStatus.Unchecked;
                break;
        }
        return data;
    }
}
