package com.codurance.training.tasks.usecase.port.out;

public class TaskIdData {
    private final long id;

    public TaskIdData(long id) {
        this.id = id;
    }

    public long value() {
        return id;
    }

    public static TaskIdData of(int id) {
        return new TaskIdData(id);
    }

    public static TaskIdData of(long id) {
        return new TaskIdData(id);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof TaskIdData)) return false;
        TaskIdData t = (TaskIdData) o;
        return id == t.value();
    }
}
