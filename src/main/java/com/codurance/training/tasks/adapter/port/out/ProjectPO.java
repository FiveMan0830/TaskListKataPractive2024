package com.codurance.training.tasks.adapter.port.out;

import java.util.List;

public class ProjectPO {
    private final String name;
    private final List<String> taskIdList;

    public ProjectPO(String name, List<String> taskIdList) {
        this.name = name;
        this.taskIdList = taskIdList;
    }

    public String getName() {
        return name;
    }

    public List<String> getTaskIdList() {
        return taskIdList;
    }
}
