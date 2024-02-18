package com.codurance.training.tasks.framework.persistant;

import com.codurance.training.tasks.adapter.port.out.TaskPO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskStore {
    private final Map<Long, TaskEntry> taskStorage;

    public TaskStore(Map<Long, TaskEntry> taskStorage) {
        this.taskStorage = taskStorage;
    }

    public void save(TaskPO taskPO) {
        taskStorage.put(taskPO.getId(), new TaskEntry(taskPO.getDescription(), taskPO.isCheck()));
    }

    public void save(List<TaskPO> taskPOs) {
        for(TaskPO task : taskPOs) {
            save(task);
        }
    }

    public Optional<TaskPO> find(long id){
        if(!taskStorage.containsKey(id)) return Optional.empty();
        TaskEntry entry = taskStorage.get(id);
        return Optional.of(new TaskPO(id, entry.description, entry.check));
    }

    private class TaskEntry {
        public String description;
        public boolean check;

        public TaskEntry(String description, boolean check) {
            this.description = description;
            this.check = check;
        }
    }
}
