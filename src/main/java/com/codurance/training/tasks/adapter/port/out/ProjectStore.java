package com.codurance.training.tasks.adapter.port.out;

import java.util.List;
import java.util.Optional;

public interface ProjectStore {
    void save(ProjectPO po);
    Optional<ProjectPO> find(String projectName);
    List<ProjectPO> findAll();
}
