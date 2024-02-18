package com.codurance.training.tasks.adapter.port.out;

import java.util.Optional;

public interface TaskStore {
    void save(TaskPO po);
    Optional<TaskPO> find(String id);
    void delete(String id);
}
