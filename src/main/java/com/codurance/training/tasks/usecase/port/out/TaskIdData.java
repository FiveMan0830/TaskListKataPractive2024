package com.codurance.training.tasks.usecase.port.out;

public class TaskIdData {
    private final String id;

    public TaskIdData(String id) {
        this.id = id;
    }

    public String value() {
        return id;
    }

    public static TaskIdData of(String id) {
        return new TaskIdData(id);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof TaskIdData)) return false;
        TaskIdData t = (TaskIdData) o;
        return id.equals(t.value());
    }
}
