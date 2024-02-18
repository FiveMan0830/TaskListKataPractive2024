package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskDescription;
import com.codurance.training.tasks.entity.TaskId;

import java.util.ArrayList;
import java.util.List;

public class TaskDataMapper {
    public static TaskData toData(Task task) {
        return new TaskData(
                TaskIdData.of(task.getId().value()),
                TaskDescriptionData.of(task.getDescription().value()),
                DueDateData.of(task.getDueDate().value()), TaskStatusDataMapper.toData(task.getStatus())
        );
    }

    public static Task toDomain(TaskData data) {
        return new Task(
                TaskId.of(data.getId().value()),
                TaskDescription.of(data.getDescription().value()),
                TaskStatusDataMapper.toDomain(data.getStatus())
        );
    }

    public static List<TaskData> toData(List<Task> tasks) {
        List<TaskData> dataList = new ArrayList<>();

        for(Task task: tasks) {
            dataList.add(toData(task));
        }

        return dataList;
    }

    public static List<Task> toDomain(List<TaskData> taskDataList) {
        List<Task> taskList = new ArrayList<>();

        for(TaskData data: taskDataList) {
            taskList.add(toDomain(data));
        }

        return taskList;
    }
}
