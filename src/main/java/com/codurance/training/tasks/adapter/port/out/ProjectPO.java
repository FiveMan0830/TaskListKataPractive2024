package com.codurance.training.tasks.adapter.port.out;

import java.util.List;

public class ProjectPO {
    private final String name;
    private final List<Long> taskIdList;

    public ProjectPO(String name, List<Long> taskIdList) {
        this.name = name;
        this.taskIdList = taskIdList;
    }

    public String getName() {
        return name;
    }

    public List<Long> getTaskIdList() {
        return taskIdList;
    }
}
