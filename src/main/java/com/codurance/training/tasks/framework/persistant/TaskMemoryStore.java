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
        taskStorage.put(taskPO.getId(), new TaskEntry(taskPO.getDescription(), taskPO.isCheck(), taskPO.getDueDate()));
    }

    public Optional<TaskPO> find(String id){
        if(!taskStorage.containsKey(id)) return Optional.empty();
        TaskEntry entry = taskStorage.get(id);
        return Optional.of(new TaskPO(id, entry.getDescription(), entry.isCheck(), entry.getDueDate()));
    }

    private class TaskEntry {
        private final String description;
        private final boolean check;
        private final String dueDate;

        public TaskEntry(String description, boolean check, String dueDate) {
            this.description = description;
            this.check = check;
            this.dueDate = dueDate;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCheck() {
            return check;
        }

        public String getDueDate() {
            return dueDate;
        }
    }
}
