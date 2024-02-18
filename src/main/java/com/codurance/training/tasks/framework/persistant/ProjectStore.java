package com.codurance.training.tasks.framework.persistant;

import com.codurance.training.tasks.adapter.port.out.ProjectPO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectStore {
    private final Map<String, List<Long>> projectStorage;


    public ProjectStore(Map<String, List<Long>> projectStorage) {
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
