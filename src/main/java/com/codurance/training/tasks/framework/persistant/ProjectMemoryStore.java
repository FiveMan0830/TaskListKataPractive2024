package com.codurance.training.tasks.framework.persistant;

import com.codurance.training.tasks.adapter.port.out.ProjectPO;
import com.codurance.training.tasks.adapter.port.out.ProjectStore;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectMemoryStore implements ProjectStore {
    private final Map<String, List<String>> projectStorage;


    public ProjectMemoryStore(Map<String, List<String>> projectStorage) {
        this.projectStorage = projectStorage;
    }

    public void save(ProjectPO projectPO) {
        projectStorage.put(projectPO.getName(), projectPO.getTaskIdList());
    }

    public Optional<ProjectPO> find(String projectName) {
        if(!projectStorage.containsKey(projectName)) return Optional.empty();
        return Optional.of(new ProjectPO(projectName, projectStorage.get(projectName)));
    }

    public List<ProjectPO> findAll() {
        return projectStorage.entrySet().stream().map(x -> new ProjectPO(x.getKey(), x.getValue())).collect(Collectors.toList());
    }
}
