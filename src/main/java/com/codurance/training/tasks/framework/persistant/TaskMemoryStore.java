package com.codurance.training.tasks.framework.persistant;

import com.codurance.training.tasks.adapter.port.out.TaskPO;
import com.codurance.training.tasks.adapter.port.out.TaskStore;

import java.util.Map;
import java.util.Optional;

public class TaskMemoryStore implements TaskStore {
    private final Map<String, TaskEntry> taskStorage;

    public TaskMemoryStore(Map<String, TaskEntry> taskStorage) {
        this.taskStorage = taskStorage;
    }

    public void save(TaskPO taskPO) {
        taskStorage.put(taskPO.getId(), new TaskEntry(taskPO.getDescription(), taskPO.isCheck()));
    }

    public Optional<TaskPO> find(String id){
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
